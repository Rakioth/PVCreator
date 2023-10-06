package com.raks.pvcreator.presentation.components

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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.raks.pvcreator.util.LocalCardSize
import com.raks.pvcreator.util.*

private const val MAX_CHARS = 18

@Composable
fun PvTextField(
    modifier:       Modifier,
    label:          Int,
    value:          String,
    onValueChange: (String) -> Unit = {},
) {
    val units                     = Units(LocalCardSize.current, LocalContext.current)
    val customTextSelectionColors = TextSelectionColors(
        handleColor     = MaterialTheme.colorScheme.outlineVariant,
        backgroundColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
    )

    var isFocused by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        Text(
            text       = stringResource(label),
            modifier   = Modifier
                  .weight(0.46f)
                  .padding(
                      top    = units.ph(2f),
                      bottom = units.ph(0.6f),
                  )
                  .fillMaxSize()
                  .wrapContentHeight(Alignment.Bottom),
            color      = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.W600,
            fontSize   = units.sh(2.5f),
            lineHeight = units.sh(3.3f),
        )
        Box(
            modifier = Modifier
                .weight(0.54f)
                .fillMaxSize()
                .border(
                    border = BorderStroke(
                        1.dp,
                        if (isFocused) MaterialTheme.colorScheme.outlineVariant else MaterialTheme.colorScheme.outline
                    ),
                    shape  = RoundedCornerShape(units.pw(2f)),
                )
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(units.pw(2f)),
                ),
        ) {
            CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
                BasicTextField(
                    value         = value,
                    onValueChange = {
                        val text = it.take(MAX_CHARS)

                        if (text.contains(Regex("[a-zA-Z\\s]")) &&
                            !text.contains(Regex("\\s{2,}")) &&
                            !text.contains(Regex("^\\s")) &&
                            text.length <= MAX_CHARS
                        ) {
                            onValueChange(text.lowercase()
                                              .replace(Regex("(?<= |^)."))
                                              { matchResult -> matchResult.value.uppercase() }
                            )
                        }
                    },
                    modifier      = Modifier
                             .padding(
                                 horizontal = units.pw(3.1),
                                 vertical   = units.ph(1.6),
                             )
                             .fillMaxSize()
                             .wrapContentHeight(Alignment.Bottom)
                             .onFocusChanged { isFocused = it.isFocused },
                    singleLine    = true,
                    cursorBrush   = SolidColor(MaterialTheme.colorScheme.outlineVariant),
                    textStyle     = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.W400,
                        fontSize   = units.sh(2.5f),
                        lineHeight = units.sh(3.3f),
                        color      = MaterialTheme.colorScheme.primary,
                    ),
                )
            }
        }
    }
}