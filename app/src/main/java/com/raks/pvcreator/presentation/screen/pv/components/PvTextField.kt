package com.raks.pvcreator.presentation.screen.pv.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.raks.pvcreator.util.*

@Composable
fun PvTextField(
    modifier: Modifier,
    label: String,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
) {
    initSize(LocalContext.current, PVCardSize.current)

    val customTextSelectionColors = TextSelectionColors(
        handleColor = MaterialTheme.colorScheme.outlineVariant,
        backgroundColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
    )

    var isFocused by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        Text(
            text     = label,
            modifier = Modifier
                .weight(0.46f)
                .padding(
                    top = 2f.ph,
                    bottom = 0.6f.ph,
                )
                .fillMaxSize()
                .wrapContentHeight(Alignment.Bottom),
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.W600,
            fontSize = 2.5f.sh,
            lineHeight = 3.3f.sh,
        )
        Box(
            modifier = Modifier
                .weight(0.54f)
                .fillMaxWidth()
                .border(
                    border = BorderStroke(1.dp, if (isFocused) MaterialTheme.colorScheme.outlineVariant else MaterialTheme.colorScheme.outline),
                    shape = RoundedCornerShape(2f.pw),
                )
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(2f.pw),
                ),
        ) {
            CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
                BasicTextField(
                    value = value,
                    onValueChange = { if (value != it) onValueChange(it) },
                    modifier = Modifier
                        .padding(
                            horizontal = 3.1.pw,
                            vertical = 1.6.ph,
                        )
                        .fillMaxSize()
                        .wrapContentHeight(Alignment.Bottom)
                        .onFocusChanged { isFocused = it.isFocused },
                    singleLine = true,
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.outlineVariant),
                    textStyle = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.W400,
                        fontSize = 2.5f.sh,
                        lineHeight = 3.3f.sh,
                        color = MaterialTheme.colorScheme.primary,
                    ),
                )
            }
        }
    }
}