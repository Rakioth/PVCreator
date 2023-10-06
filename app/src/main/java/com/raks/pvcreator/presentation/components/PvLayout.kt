package com.raks.pvcreator.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.raks.pvcreator.R
import com.raks.pvcreator.presentation.viewmodels.PvViewModel
import com.raks.pvcreator.util.stringResName

@Composable
fun PvLayout(
    paddingValues: PaddingValues,
    viewModel:     PvViewModel   = hiltViewModel(),
) {
    var text by remember { mutableStateOf("opa") }

    PvCard(
        paddingValues = paddingValues,
        textfields    = {
            PvTextField(
                modifier = Modifier
                    .weight(0.12f)
                    .fillMaxWidth(),
                label    = R.string.label_card,
                value    = stringResName("label_none"),
            )
            PvTextField(
                modifier = Modifier
                    .weight(0.12f)
                    .fillMaxWidth(),
                label    = R.string.label_item,
                value    = stringResName("label_none"),
            )
            PvTextField(
                modifier = Modifier
                    .weight(0.12f)
                    .fillMaxWidth(),
                label    = R.string.label_variant,
                value    = stringResName("label_none"),
            )
            PvTextField(
                modifier = Modifier
                    .weight(0.12f)
                    .fillMaxWidth(),
                label    = R.string.label_wildcard,
                value    = stringResName("label_none"),
            )
            PvTextField(
                modifier      = Modifier
                         .weight(0.12f)
                         .fillMaxWidth(),
                label         = R.string.label_name,
                value         = text,
                onValueChange = { text = it },
            )
        },
        barcode       = {
            PvBarcode(
                modifier       = Modifier
                          .weight(0.33f)
                          .fillMaxSize(),
                barcodePattern = listOf(
                    "10010001010011101100010100001000100101000010100111010001100110110001101011100101100010100001000010100100010011011",
                    "10001011000100101000110010001000100101100110011101011000100011010011001010000101000110100111011100101110010011001"
                ),
            )
        }
    )
}