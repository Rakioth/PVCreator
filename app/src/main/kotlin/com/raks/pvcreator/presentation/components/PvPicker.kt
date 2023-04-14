package com.raks.pvcreator.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.raks.pvcreator.domain.model.PickerOption

@Composable
fun PvPicker(
    options:            List<PickerOption>,
    modifier:           Modifier                   = Modifier,
    startIndex:         Int                        = 0,
    rowCount:           Int                        = 7,
    height:             Dp                         = 238.dp,
    style:              TextStyle                  = MaterialTheme.typography.titleLarge,
    color:              Color                      = MaterialTheme.colorScheme.secondary,
    selectorProperties: SelectorProperties         = WheelPickerDefaults.selectorProperties(
        enabled = true,
        shape   = RoundedCornerShape(8.dp),
        color   = MaterialTheme.colorScheme.secondaryContainer,
        border  = null,
    ),
    onScrollFinished:  (PickerOption, Int) -> Unit,
) {
    WheelPicker(
        options            = options,
        count              = options.size,
        rowCount           = rowCount,
        modifier           = modifier,
        startIndex         = startIndex,
        height             = height,
        selectorProperties = selectorProperties,
        onScrollFinished   = onScrollFinished,
    ) { index ->
        Text(
            text     = options[index].ref,
            style    = style,
            color    = color,
            maxLines = 1,
        )
    }
}
