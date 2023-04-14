package com.raks.pvcreator.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raks.pvcreator.domain.model.PickerOption
import com.raks.pvcreator.util.LocalCardSize
import com.raks.pvcreator.util.Units
import com.raks.pvcreator.util.stringResName

private const val MAX_CHARS = 18

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ColumnScope.PvTextField(
    label:             Int,
    modifier:          Modifier              = Modifier,
    options:           List<PickerOption>    = emptyList(),
    value:             String                = "",
    isVisible:         Boolean               = true,
    isPicker:          Boolean               = true,
    onScrollFinished: (PickerOption) -> Unit = {},
    onValueChange:    (String)       -> Unit = {},
) {
    val focusManager              = LocalFocusManager.current
    val imeIsVisible              = WindowInsets.isImeVisible
    val optionsRef                = options.map { it.copy(ref = stringResName(it.ref)) }
    val units                     = Units(LocalCardSize.current, LocalContext.current)
    val customTextSelectionColors = TextSelectionColors(
        handleColor     = MaterialTheme.colorScheme.outlineVariant,
        backgroundColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f),
    )

    var text        by remember { mutableStateOf(value) }
    var index       by remember { mutableIntStateOf(0)  }
    var isFocused   by remember { mutableStateOf(false) }
    var isSheetOpen by remember { mutableStateOf(false) }

    LaunchedEffect(imeIsVisible) {
        if (!imeIsVisible) focusManager.clearFocus()
    }

    LaunchedEffect(optionsRef) {
        if (optionsRef.isNotEmpty() && isPicker) {
            onScrollFinished(optionsRef[0])
            text  = optionsRef[0].ref
            index = 0
        }
    }

    if (isVisible) {
        Column(
            modifier = modifier
                .weight(0.12f)
                .fillMaxWidth()
        ) {
            Text(
                text     = stringResource(label),
                modifier = Modifier
                    .weight(0.46f)
                    .padding(
                        top    = units.ph(2f),
                        bottom = units.ph(0.8f),
                    )
                    .fillMaxSize()
                    .wrapContentHeight(Alignment.Bottom),
                maxLines = 1,
                style    = TextStyle(
                       fontFamily    = FontFamily.Default,
                       fontWeight    = FontWeight.W600,
                       fontSize      = units.sh(2.5f),
                       lineHeight    = units.sh(3.3f),
                       letterSpacing = 0.sp,
                       color         = MaterialTheme.colorScheme.primary,
                ),
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
                    )
                    .then(
                        if (isPicker)
                            Modifier
                                .clip(RoundedCornerShape(units.pw(2f)))
                                .clickable(onClick = {
                                    isFocused   = true
                                    isSheetOpen = true
                                })
                        else Modifier
                    ),
            ) {
                CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
                    BasicTextField(
                        value         = text,
                        onValueChange = { inputText ->
                            val filteredText = inputText
                                .take(MAX_CHARS)
                                .filter { it.isLetter() || it == ' ' }
                                .replace(Regex("^\\s"), "")
                                .replace(Regex("\\s+"), " ")
                                .lowercase()
                                .split(" ")
                                .joinToString(" ") { it.replaceFirstChar(Char::titlecase) }

                            if (filteredText.length <= MAX_CHARS) {
                                text = filteredText
                                onValueChange(filteredText.trim())
                            }
                        },
                        enabled       = !isPicker,
                        modifier      = Modifier
                                 .padding(
                                     start  = units.pw(3.1),
                                     top    = units.ph(1.4),
                                     end    = units.pw(3.1),
                                     bottom = units.ph(1.8),
                                 )
                                 .fillMaxSize()
                                 .wrapContentHeight(Alignment.Bottom)
                                 .onFocusChanged { isFocused = it.isFocused },
                        singleLine    = true,
                        cursorBrush   = SolidColor(MaterialTheme.colorScheme.outlineVariant),
                        textStyle     = TextStyle(
                                fontFamily    = FontFamily.Default,
                                fontWeight    = FontWeight.W400,
                                fontSize      = units.sh(2.5f),
                                lineHeight    = units.sh(3.3f),
                                letterSpacing = 0.sp,
                                color         = MaterialTheme.colorScheme.primary,
                        ),
                    )
                }
            }
        }

        if (isPicker)
            PvBottomSheet(
                options          = optionsRef,
                isSheetOpen      = isSheetOpen,
                startIndex       = index,
                onScrollFinished = { pickerOption: PickerOption, i: Int ->
                    onScrollFinished(pickerOption)
                    text  = pickerOption.ref
                    index = i
                },
                onDismissRequest = {
                    isFocused   = false
                    isSheetOpen = false
                }
            )
    } else {
        Spacer(modifier = Modifier.weight(0.12f))
    }
}
