package com.raks.pvcreator.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

private const val BARCODE_ROWS    = 4
private const val BARCODE_COLUMNS = 113

@Composable
fun RowScope.PvBarcode(
    barcodePattern: List<String>,
    modifier:       Modifier     = Modifier,
    barcodeColor:   Color        = MaterialTheme.colorScheme.primary,
) {
    Canvas(
        modifier = modifier
            .weight(0.33f)
            .fillMaxSize(),
        onDraw   = {
            val barcodeWidth   = size.width  / BARCODE_ROWS
            val barcodeHeight  = size.height / BARCODE_COLUMNS
            val barcodeTrimmed = barcodePattern.take(BARCODE_ROWS)

            barcodeTrimmed.forEachIndexed { index, row ->
                val reverseIndex = barcodeTrimmed.size - index
                val x            = size.width - barcodeWidth * reverseIndex

                row.forEachIndexed { offset, char ->
                    val color = if (char == '1') barcodeColor else Color.Transparent
                    val y     = offset * barcodeHeight
                    drawRect(
                        color,
                        Offset(x, y),
                        Size(barcodeWidth, barcodeHeight),
                    )
                }
            }
        }
    )
}
