package com.raks.pvcreator.presentation.components

import android.graphics.Bitmap
import android.view.View
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.graphics.applyCanvas
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.airbnb.lottie.compose.*
import com.raks.pvcreator.R
import com.raks.pvcreator.presentation.PvScreen
import com.raks.pvcreator.presentation.events.SplashEvent
import com.raks.pvcreator.ui.MainViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlin.math.roundToInt

private lateinit var box: String

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashTopBar(
    viewModel: MainViewModel = hiltViewModel(LocalView.current.findViewTreeViewModelStoreOwner()!!),
) {
    val state = viewModel.state.value!!
    
    val isDarkTheme = LocalTheme.current

    val snackbarHostState = remember { SnackbarHostState() }
    val scope             = rememberCoroutineScope()


    println("DIABLO" + isDarkTheme)

    if (!::box.isInitialized) {
        box = if (isDarkTheme) R.raw.pv_switch_dark.toString() else R.raw.pv_switch_light.toString()
    }

    val theone = if (isDarkTheme) R.raw.pv_background_dark else R.raw.pv_background_light


    var onclick by remember { mutableStateOf(false) }

    val view = LocalView.current
    var capturingViewBounds by remember { mutableStateOf<Rect?>(null) }
    var background          by remember { mutableStateOf<Bitmap?>(null) }
    val composition         by rememberLottieComposition(LottieCompositionSpec.RawRes(box.toInt()))
    val progress            by animateLottieCompositionAsState(
        composition = composition,
        isPlaying   = true,
        speed       = if (onclick) 1.7f else -2.2f,
//        speed       = if (flag) -2.2f else if (isDarkTheme) 1.7f else -2.2f,
//        speed       = if (isDarkTheme) 1.7f else -2.2f,
        clipSpec    = LottieClipSpec.Frame(max = 50),
    )



    LaunchedEffect(state.startThemeTransition) {
        val tasks = arrayListOf<Deferred<Unit>>()
        tasks.add(async {
            animate(
                initialValue  = 0f,
                targetValue   = if (!isDarkTheme) 2500f else 0f,
                animationSpec = tween(1500),
                block         = { i, _ -> viewModel.onEvent(SplashEvent.UpdateLightToDarkRadius(i)) })

        })
        tasks.add(async {
            animate(
                initialValue  = 2500f,
                targetValue   = if (isDarkTheme) 0f else 2500f,
                animationSpec = tween(1000),
                block         = { i, _ -> viewModel.onEvent(SplashEvent.UpdateDarkToLightRadius(i)) })
        })
        tasks.awaitAll()
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar       = {
            CenterAlignedTopAppBar(
                title  = {
                    LottieAnimation(
                        composition = composition,
                        progress    = { progress },
                        modifier    = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .clip(CircleShape)
                            .clickable {
                                capturingViewBounds?.let {
                                    background = takeScreenShot(it, view)
                                    viewModel.onEvent(SplashEvent.ToggleThemeTransitionState)
                                    onclick = !onclick
                                    viewModel.onEvent(SplashEvent.ToggleDarkTheme)
                                }
                            },
                    )

//                            scope.launch {
//                                if (!themeConfig.isThemeActive) {
//                                    snackbarHostState.showSnackbar("🎨  Activating Theme Switch...")
//                                    delay(500)
//                                }
//                                viewModel.onEvent(PvEvent.InputTheme(darkTheme))
//                            }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned { capturingViewBounds = it.boundsInRoot() },
        ) {
            SplashAnimation()


            PvScreen(paddingValues)
            background?.let {
                SplashSwitcher(
                    darkToLightRadius = state.darkToLightRadius,
                    lightToDarkRadius = state.lightToDarkRadius,
                    it,
                )
            }
        }
    }
}

private fun takeScreenShot(bounds: Rect, view: View) =
    Bitmap.createBitmap(
        bounds.width.roundToInt(),
        bounds.height.roundToInt(),
        Bitmap.Config.ARGB_8888,
    ).applyCanvas { view.draw(this) }