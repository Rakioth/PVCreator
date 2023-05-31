package com.raks.pvcreator.util

import android.graphics.Bitmap
import android.graphics.Typeface
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameMillis
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.whenStarted
import kotlinx.coroutines.launch
import java.nio.IntBuffer
import java.util.UUID
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.sqrt

const val LOG_TAG = "PlasmaBackground"

// val HEIGHT_MAPS = buildHeightMap()
private var GRADIENT_COLOR_PALETTE: Array<Color> = emptyArray()
private var GRADIENT_COLOR_PALETTE_RGBA: List<Int> = emptyList()

// var CURRENT_IMAGE: IntArray = IntArray(0)
private var CURRENT_IMAGE_BUFFER: IntBuffer? = null
private var BITMAP_FOR_DRAWING: Bitmap? = null
private var LAST_MEASUREMENT: Long = 0
private var LAST_TICK: Long = 0
private var FRAMES_PER_SECOND_COUNTER = 0
private var LAST_FRAME_RATE = 0

const val DEFAULT_FPS = 20

@Composable
fun PlasmaBackground(
    modifier: Modifier = Modifier,
    colors: Array<Color> = arrayOf(),
    maxFPS: Int = DEFAULT_FPS,
    debugShowFPS: Boolean = false,
    debugColor: Color = Color(0x4DFF00FB),
    debugDoNotScale: Boolean = false,
) {
    MaterialTheme {
        val filledColor = if (colors.isEmpty()) {
            arrayOf(
                MaterialTheme.colorScheme.primary,
                interpolate(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.background,
                    0.5f
                ),
                MaterialTheme.colorScheme.background,
                interpolate(
                    MaterialTheme.colorScheme.background,
                    MaterialTheme.colorScheme.secondary,
                    0.5f
                ),
                MaterialTheme.colorScheme.secondary
            )
        } else {
            colors
        }
        var frameTime by remember { mutableStateOf(0L) }

        GRADIENT_COLOR_PALETTE = buildGradientColors(filledColor, debugColor)
        GRADIENT_COLOR_PALETTE_RGBA = GRADIENT_COLOR_PALETTE.map { it.toRGBA8888() }
        val minUpdateTime = floor(1000f / maxFPS).toInt()
        Log.d(LOG_TAG, "Draw with maximum of $maxFPS FPS")

        val lifecycleOwner = LocalLifecycleOwner.current
        Box(modifier = modifier) {
            LaunchedEffect(key1 = UUID.randomUUID().toString()) {
                lifecycleOwner.whenStarted {
                    this.launch {
                        while (true) {
                            // this will be called for each frame
                            // by updating `remember` value we initiating EachFrameUpdatingCanvas redraw
                            val currentFrameTimeInMilliseconds = withFrameMillis { it }
                            val timePassed = currentFrameTimeInMilliseconds - LAST_TICK
                            if (timePassed < minUpdateTime) {
                                continue
                            }
                            frameTime = currentFrameTimeInMilliseconds
                            LAST_TICK = frameTime
                        }
                    }
                }
            }

            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                val rawWidth = this.maxWidth
                val rawHeight = this.maxHeight
                val width = this.maxWidth.value.roundToInt()
                val height = this.maxHeight.value.roundToInt()
                val fullWidth = with(LocalDensity.current) { rawWidth.toPx().toInt() }
                val fullHeight = with(LocalDensity.current) { rawHeight.toPx().toInt() }

                val paint = Paint().asFrameworkPaint()
                paint.apply {
                    isAntiAlias = true
                    textSize = 48f
                    typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                    color = Color.White.toArgb()
                }

                val mapSize = max(width, height) * 2
                val heightMaps = buildHeightMap(mapSize)

                Log.d(LOG_TAG, "Try to paint on Size($width/$height) with heightmap of ($mapSize)")

                CURRENT_IMAGE_BUFFER = IntBuffer.allocate(width * height)
                CURRENT_IMAGE_BUFFER?.mark()
                var imageWasDrawn = false

                if (heightMaps.map1.isEmpty() || heightMaps.map2.isEmpty()) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        this.drawRect(debugColor, size = Size(1f, 1f))
                    }
                } else {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        // current palette is established during animation
                        val heightMapPosition = moveHeightMap(frameTime, mapSize)

                        if (frameTime > LAST_MEASUREMENT + 1000) {
                            LAST_MEASUREMENT = frameTime
                            LAST_FRAME_RATE = FRAMES_PER_SECOND_COUNTER
                            FRAMES_PER_SECOND_COUNTER = 0
                        }
                        FRAMES_PER_SECOND_COUNTER += 1

                        CURRENT_IMAGE_BUFFER?.rewind()
                        for (x in 0 until height) {
                            for (y in 0 until width) {
                                // indexes into height maps for pixel
                                val i =
                                    (x + heightMapPosition.dx1) * mapSize + (y + heightMapPosition.dy1)
                                val k =
                                    (x + heightMapPosition.dx2) * mapSize + (y + heightMapPosition.dy2)

                                // height value of 0..255
                                val h =
                                    (heightMaps.map1[i] + heightMaps.map2[k]).toInt()
                                // get color value from current palette
                                val c = GRADIENT_COLOR_PALETTE_RGBA[h]

                                // set pixel data
                                CURRENT_IMAGE_BUFFER?.put(c)
                            }
                        }
                        CURRENT_IMAGE_BUFFER?.clear()
                        if (!imageWasDrawn) {
                            // WARNING the height / width parameters are reversed, this is right! do not change
                            BITMAP_FOR_DRAWING =
                                Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
                            imageWasDrawn = true
                        } else {
                            BITMAP_FOR_DRAWING?.let { bitmap ->
                                bitmap.copyPixelsFromBuffer(CURRENT_IMAGE_BUFFER)
                                val destinationSize = if (!debugDoNotScale) {
                                    IntSize(fullWidth, fullHeight * 2)
                                } else {
                                    IntSize(width, height)
                                }
                                this.drawImage(
                                    image = bitmap.asImageBitmap(),
                                    srcSize = IntSize(width, height),
                                    dstSize = destinationSize
                                )
                            }
                        }
                        if (debugShowFPS) {
                            drawIntoCanvas {
                                it.nativeCanvas.drawText(
                                    "$LAST_FRAME_RATE FPS",
                                    20f,
                                    60f,
                                    paint
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun Color.toRGBA8888(): Int {
    val colorInt = this.toArgb()
    val a = (colorInt shr 24) and 255
    val r = (colorInt shr 16) and 255
    val g = (colorInt shr 8) and 255
    val b = colorInt and 255
    return (a shl 24) or (b shl 16) or (g shl 8) or r
}

data class HeightMaps(
    val map1: DoubleArray = DoubleArray(0),
    val map2: DoubleArray = DoubleArray(0)
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HeightMaps

        if (!map1.contentEquals(other.map1)) return false
        if (!map2.contentEquals(other.map2)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = map1.contentHashCode()
        result = 31 * result + map2.contentHashCode()
        return result
    }
}

private fun buildHeightMap(mapSize: Int): HeightMaps {
    // init height map 1
    val heightMap1 = DoubleArray(mapSize * mapSize)
    val heightMap2 = DoubleArray(mapSize * mapSize)

    for (x in 0 until mapSize) {
        for (y in 0 until mapSize) {
            // index of coordinate in height map array
            val index = x * mapSize + y

            // u,v are coordinates with origin at upper left corner
            // cx and cy are coordinates with origin at the
            // center of the map
            val cx = x - mapSize / 2
            val cy = y - mapSize / 2

            // distance from middle of map
            val d = distance(cx, cy)

            // stretching so we get the desired ripple density on our map
            val stretch = (3 * Math.PI) / (mapSize / 2)

            // wavy height value between -1 and 1
            val ripple = sin(d * stretch)

            // wavy height value normalized to 0..1
            val normalized = (ripple + 1) / 2

            // height map value 0..128, integer
            heightMap1[index] = floor(normalized * 128)
        }
    }

    for (x in 0 until mapSize) {
        for (y in 0 until mapSize) {
            val i = x * mapSize + y
            val cx = x - mapSize / 2
            val cy = y - mapSize / 2

            // skewed distance as input to chaos field calculation,
            // scaled for smoothness over map distance
            val d1 = distance(0.8 * cx, 1.3 * cy) * 0.022
            val d2 = distance(1.35 * cx, 0.45 * cy) * 0.022

            val s = sin(d1)
            val c = cos(d2)
            // height value between -2 and +2
            val h = s + c

            // height value between 0..1
            val normalized = (h + 2) / 4
            // height value between 0..127, integer
            heightMap2[i] = floor(normalized * 127)
        }
    }

    return HeightMaps(map1 = heightMap1, map2 = heightMap2)
}

private fun buildGradientColors(
    colors: Array<Color>,
    defaultColor: Color
): Array<Color> {
    val gradientColors: Array<Color> = Array(256) { defaultColor }
    if (colors.size != 5) {
        TODO("TODO: Fix me, i can only handle 5 colors...")
    }
    for (index in 0..63) {
        val factor = index / 64f
        gradientColors[index] = interpolate(colors[0], colors[1], factor)
    }
    for (index in 64..127) {
        val factor = (index - 64) / 64f
        gradientColors[index] = interpolate(colors[1], colors[2], factor)
    }

    for (index in 128..191) {
        val factor = (index - 128) / 64f
        gradientColors[index] = interpolate(colors[2], colors[3], factor)
    }

    for (index in 192..255) {
        val factor = (index - 192) / 64f
        gradientColors[index] = interpolate(colors[3], colors[4], factor)
    }
    return gradientColors
}

private fun distance(x: Int, y: Int): Double {
    return sqrt((x * x + y * y).toDouble())
}

private fun distance(x: Double, y: Double): Double {
    return sqrt((x * x + y * y))
}

private fun interpolate(c1: Color, c2: Color, f: Float): Color {
    val red = min(1f, max(0f, c1.red + (c2.red - c1.red) * f))
    val green = min(1f, max(0f, c1.green + (c2.green - c1.green) * f))
    val blue = min(1f, max(0f, c1.blue + (c2.blue - c1.blue) * f))

    return Color(red = red, green = green, blue = blue)
}

private fun moveHeightMap(timeIndex: Long, mapSize: Int): HeightMapPosition {
    val dx1 = floor((((cos(timeIndex * 0.0002 + 0.4 + Math.PI) + 1) / 2) * mapSize) / 2).toInt()
    val dy1 = floor((((cos(timeIndex * 0.0003 - 0.1) + 1) / 2) * mapSize) / 2).toInt()
    val dx2 = floor((((cos(timeIndex * -0.0002 + 1.2) + 1) / 2) * mapSize) / 2).toInt()
    val dy2 = floor((((cos(timeIndex * -0.0003 - 0.8 + Math.PI) + 1) / 2) * mapSize) / 2).toInt()

    return HeightMapPosition(dx1 = dx1, dy1 = dy1, dx2 = dx2, dy2 = dy2)
}

data class HeightMapPosition(
    val dx1: Int = 0,
    val dy1: Int = 0,
    val dx2: Int = 0,
    val dy2: Int = 0,
)