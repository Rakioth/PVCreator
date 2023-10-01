package com.raks.pvcreator.presentation.components

import android.graphics.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp

@Composable
fun SplashSwitcher(
    darkToLightRadius: Float,
    lightToDarkRadius: Float,
    background:        Bitmap,
) {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.99f),
        onDraw   = {
            drawIntoCanvas {
                val clipPath = Path()

                clipPath.addOval(
                    Rect(
                        center = Offset(
                            x = size.width.dp.toPx() / 6,
                            y = 50.dp.toPx(),
                        ),
                        radius = darkToLightRadius,
                    )
                )

                it.clipPath(clipPath)

                val transparentPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                    color    = Color.TRANSPARENT
                    xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
                }

                it.nativeCanvas.drawBitmap(
                    background,
                    0f,
                    0f,
                    Paint(),
                )

                it.nativeCanvas.drawCircle(
                    size.width.dp.toPx() / 6,
                    50.dp.toPx(),
                    lightToDarkRadius,
                    transparentPaint,
                )
            }
        }
    )
}