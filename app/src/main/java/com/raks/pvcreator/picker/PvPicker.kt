package com.raks.pvcreator.picker

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.raks.pvcreator.domain.model.PickerOption

@Composable
fun PvPicker(
    modifier: Modifier = Modifier,
    options: List<PickerOption>,
    size: DpSize = DpSize(128.dp, 128.dp),
    style: TextStyle = MaterialTheme.typography.titleMedium,
    color: Color = LocalContentColor.current,
    selectorProperties: SelectorProperties = WheelPickerDefaults.selectorProperties(),
    onScrollFinished: (snappedOption: PickerOption?) -> Unit = {},
) {
    WheelPicker(
        modifier = modifier,
        options = options,
        size = size,
        selectorProperties = selectorProperties,
        onScrollFinished = onScrollFinished
    ) { index ->
        Text(
            text = options[index].ref,
            style = style,
            color = color,
            maxLines = 1
        )
    }
}