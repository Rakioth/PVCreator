package com.raks.pvcreator.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.raks.pvcreator.R
import com.raks.pvcreator.util.LocalCardSize
import com.raks.pvcreator.util.LocalTheme

@Composable
fun PvCard(
    paddingValues: PaddingValues,
    darkTheme:     Boolean       = LocalTheme.current,
    painter:       Painter       = painterResource(R.drawable.pv_card_light),
    padding:       Dp            = 15.dp,
    textfields: @Composable ColumnScope.() -> Unit,
    barcode:    @Composable RowScope.()    -> Unit,
) {
    val ratio = painter.intrinsicSize.width / painter.intrinsicSize.height

    var size by remember { mutableStateOf(IntSize.Zero) }

    Box(
        modifier = Modifier
            .padding(
                start  = padding,
                top    = padding + paddingValues.calculateTopPadding(),
                end    = padding,
                bottom = padding + paddingValues.calculateBottomPadding(),
            )
            .wrapContentSize(Alignment.TopCenter)
            .aspectRatio(ratio)
            .then(
                if (ratio > 1)
                    Modifier.height(IntrinsicSize.Max)
                else
                    Modifier.width(IntrinsicSize.Max)
            )
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
                Spacer(modifier = Modifier.weight(0.33f))
                CompositionLocalProvider(LocalCardSize provides size) {
                    textfields()
                }
                Spacer(modifier = Modifier.weight(0.07f))
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
