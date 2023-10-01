package com.raks.pvcreator.presentation.screen.pv

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.*
import com.raks.pvcreator.R
import com.raks.pvcreator.presentation.screen.pv.components.PvBarcode
import com.raks.pvcreator.presentation.screen.pv.components.PvCard
import com.raks.pvcreator.presentation.screen.pv.components.PvTextField
import com.raks.pvcreator.presentation.screen.splash.components.SplashTopBar
import com.raks.pvcreator.util.LocalTheme

@Composable
fun PvScreen(
    paddingValues: PaddingValues,
    isDarkTheme: Boolean = LocalTheme.current,
    viewModel: PvViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    var text by remember { mutableStateOf(TextFieldValue("Text")) }

//        PlasmaBackground(
//            colors = arrayOf(
//                MaterialTheme.colorScheme.surface,
//                MaterialTheme.colorScheme.onSurface,
//                MaterialTheme.colorScheme.surfaceVariant,
//                MaterialTheme.colorScheme.onSurfaceVariant,
//                MaterialTheme.colorScheme.surfaceTint,
//            ),
//        )
        PvCard(
            darkTheme = isDarkTheme,
            painter = painterResource(R.drawable.pv_card_light),
            paddingValues = paddingValues,
            textfields = {
                PvTextField(
                    modifier = Modifier
                        .weight(0.12f)
                        .fillMaxWidth(),
                    label = "Title",
                    value = text,
                    onValueChange = { text = it },
                )
                PvTextField(
                    modifier = Modifier
                        .weight(0.12f)
                        .fillMaxWidth(),
                    label = "Title",
                    value = text,
                    onValueChange = { text = it },
                )
                PvTextField(
                    modifier = Modifier
                        .weight(0.12f)
                        .fillMaxWidth(),
                    label = "Title",
                    value = text,
                    onValueChange = { text = it },
                )
                PvTextField(
                    modifier = Modifier
                        .weight(0.12f)
                        .fillMaxWidth(),
                    label = "Title",
                    value = text,
                    onValueChange = { text = it },
                )
                PvTextField(
                    modifier = Modifier
                        .weight(0.12f)
                        .fillMaxWidth(),
                    label = "Title",
                    value = text,
                    onValueChange = { text = it },
                )
            },
            barcode = {
                PvBarcode(
                    modifier = Modifier
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