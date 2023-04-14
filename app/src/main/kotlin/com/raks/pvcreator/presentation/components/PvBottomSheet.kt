package com.raks.pvcreator.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.raks.pvcreator.domain.model.PickerOption

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PvBottomSheet(
    options:           List<PickerOption>,
    isSheetOpen:       Boolean,
    startIndex:        Int,
    onScrollFinished: (PickerOption, Int) -> Unit,
    onDismissRequest: ()                  -> Unit,
    padding:          Dp                          = 20.dp,
) {
    if (isSheetOpen)
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            modifier         = Modifier.padding(horizontal = 15.dp),
            sheetState       = rememberModalBottomSheetState(),
            shape            = RoundedCornerShape(28.0.dp),
            containerColor   = MaterialTheme.colorScheme.tertiaryContainer,
            scrimColor       = Color.Transparent,
        ) {
            PvPicker(
                options          = options,
                modifier         = Modifier
                            .padding(
                                start  = padding,
                                end    = padding,
                                bottom = padding,
                            ),
                startIndex       = startIndex,
                onScrollFinished = onScrollFinished,
            )
        }
}
