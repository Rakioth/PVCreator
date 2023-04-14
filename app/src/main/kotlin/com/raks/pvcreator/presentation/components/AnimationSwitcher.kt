package com.raks.pvcreator.presentation.components

import android.graphics.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInRoot
import com.raks.pvcreator.presentation.states.ThemeState

@Composable
fun AnimationSwitcher(
    state:    ThemeState,
    position: LayoutCoordinates?,
    view:     Bitmap?,
) {
    if (position != null && view != null)
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.99f),
            onDraw   = {
                drawIntoCanvas {
                    val clipPath = Path()
                    val x        = position.positionInRoot().x + (position.size.width  / 2)
                    val y        = position.positionInRoot().y + (position.size.height / 2)

                    clipPath.addOval(
                        Rect(
                            center = Offset(x, y),
                            radius = state.darkToLightRadius,
                        )
                    )

                    it.clipPath(clipPath)

                    val transparentPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                        color    = Color.TRANSPARENT
                        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
                    }

                    it.nativeCanvas.drawBitmap(
                        view,
                        0f,
                        0f,
                        Paint(),
                    )

                    it.nativeCanvas.drawCircle(
                        x,
                        y,
                        state.lightToDarkRadius,
                        transparentPaint,
                    )
                }
            }
        )
}
