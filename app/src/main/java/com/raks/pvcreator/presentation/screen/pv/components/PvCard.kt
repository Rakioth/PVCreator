package com.raks.pvcreator.presentation.screen.pv.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.raks.pvcreator.R
import com.raks.pvcreator.util.initSize

@Composable
fun PvCard(
    darkTheme:     Boolean,
    painter:       Painter,
    paddingValues: PaddingValues,
    textfields: @Composable ColumnScope.() -> Unit,
    barcode:    @Composable RowScope.()    -> Unit,
) {
    val ratio = painter.intrinsicSize.width / painter.intrinsicSize.height
    var size by remember { mutableStateOf(IntSize.Zero) }

    initSize(LocalContext.current, size)

    Box(
        modifier = Modifier
            .padding(
                top    = paddingValues.calculateTopPadding() + 15.dp,
                start  = 15.dp,
                end    = 15.dp,
                bottom = 15.dp,
            )
            .wrapContentSize(Alignment.TopCenter)
            .aspectRatio(ratio)
            .let {
                if (ratio > 1)
                    it.height(IntrinsicSize.Max)
                else
                    it.width(IntrinsicSize.Max)
            }
            .onSizeChanged { size = it },
    ) {
        Image(
            painter            = if (darkTheme) painterResource(R.drawable.pv_card_dark) else painter,
            contentDescription = stringResource(R.string.label_pvcard),
            modifier           = Modifier.fillMaxSize(),
        )
        Row {
            Spacer(modifier = Modifier.weight(0.127f))
            Column(modifier = Modifier.weight(0.683f))
            {
                Spacer(modifier = Modifier.weight(0.34f))
                textfields()
                Spacer(modifier = Modifier.weight(0.06f))
            }
            Column(modifier = Modifier.weight(0.19f))
            {
                Spacer(modifier = Modifier.weight(0.08f))
                Row(modifier    = Modifier.weight(0.84f))
                {
                    Spacer(modifier = Modifier.weight(0.33f))
                    barcode()
                    Spacer(modifier = Modifier.weight(0.33f))
                }
                Spacer(modifier = Modifier.weight(0.08f))
            }
        }
    }
}