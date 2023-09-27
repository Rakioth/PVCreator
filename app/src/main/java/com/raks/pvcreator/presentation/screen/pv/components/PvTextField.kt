package com.raks.pvcreator.presentation.screen.pv.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.raks.pvcreator.util.ph
import com.raks.pvcreator.util.pw
import com.raks.pvcreator.util.sh

@Composable
fun PvTextField(
    modifier: Modifier,
    label:    String,
    value:    TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text       = label,
            modifier   = Modifier
                .weight(0.46f)
                .padding(
                    top    = 2f.ph,
                    bottom = 0.6f.ph,
                )
                .fillMaxSize()
                .wrapContentHeight(Alignment.Bottom),
            color      = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.W600,
            fontSize   = 2.5f.sh,
            lineHeight = 3.3f.sh,
        )
        Box(
            modifier = Modifier
                .weight(0.54f)
                .fillMaxWidth()
                .border(
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                    shape  = RoundedCornerShape(2f.pw),
                )
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(2f.pw),
                ),
        ) {
            BasicTextField(
                value         = value,
                onValueChange = { if (value != it) onValueChange(it) },
                modifier      = Modifier
                    .padding(
                        horizontal = 3.1.pw,
                        vertical   = 1.6.ph,
                    )
                    .fillMaxSize()
                    .wrapContentHeight(Alignment.Bottom),
                singleLine    = true,
                cursorBrush   = SolidColor(MaterialTheme.colorScheme.primary),
                textStyle     = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.W400,
                    fontSize   = 2.5f.sh,
                    lineHeight = 3.3f.sh,
                    color      = MaterialTheme.colorScheme.primary,
                ),
            )
        }
    }
}