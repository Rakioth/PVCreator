package com.raks.pvcreator.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.raks.pvcreator.domain.model.PickerOption
import com.raks.pvcreator.util.stringResName

@Composable
fun PvPicker(
    options:  List<PickerOption>,
    modifier: Modifier           = Modifier,
    size:     DpSize             = DpSize(128.dp, 128.dp),
    onScrollFinished: (snappedOption: PickerOption?) -> Unit = {},
) {
    WheelPicker(
        modifier           = modifier,
        size               = size,
        selectorProperties = WheelPickerDefaults.selectorProperties(
            enabled = true,
            shape   = RoundedCornerShape(16.dp),
            color   = MaterialTheme.colorScheme.secondary,
            border  = null,
        ),
        options            = options,
        onScrollFinished   = onScrollFinished,
    ) { index ->
        Text(
            text     = stringResName(options[index].ref),
            style    = MaterialTheme.typography.bodyLarge,
            color    = MaterialTheme.colorScheme.tertiary,
            maxLines = 1,
        )
    }
}