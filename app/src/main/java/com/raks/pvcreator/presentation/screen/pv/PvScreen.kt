package com.raks.pvcreator.presentation.screen.pv

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.raks.pvcreator.R
import com.raks.pvcreator.domain.model.ThemeConfig
import com.raks.pvcreator.presentation.screen.pv.components.PvBarcode
import com.raks.pvcreator.presentation.screen.pv.components.PvCard
import com.raks.pvcreator.presentation.screen.pv.components.PvTextField
import com.raks.pvcreator.presentation.components.ThemeSwitcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PvScreen(
    themeConfig: ThemeConfig,
    viewModel: PvViewModel = hiltViewModel(LocalView.current.findViewTreeViewModelStoreOwner()!!),
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()


    var text by remember { mutableStateOf(TextFieldValue("Text")) }

    val darkTheme   = themeConfig.themeIcon.let {
        when (it) {
            com.raks.pvcreator.domain.model.ThemeIcon.DEFAULT -> isSystemInDarkTheme()
            com.raks.pvcreator.domain.model.ThemeIcon.LIGHT   -> false
            com.raks.pvcreator.domain.model.ThemeIcon.DARK    -> true
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    ThemeSwitcher(
                        darkTheme = darkTheme,
                        onClick = {
                            scope.launch {
                                if (!themeConfig.isThemeActive) {
                                    snackbarHostState.showSnackbar("🎨  Activating Theme Switch...")
                                    delay(500)
                                }
                                viewModel.onEvent(PvEvent.InputTheme(darkTheme))
                            }
                        }
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Transparent
                ),
                actions = {
                    Text(
                        text = themeConfig.isThemeActive.toString()
                    )
                    Text(
                        text = themeConfig.themeIcon.name
                    )
                }
            )
        },
    ) {

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
            darkTheme = darkTheme,
            painter = painterResource(R.drawable.ic_pv_card_light),
            paddingValues = it,
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
}