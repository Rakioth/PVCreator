package com.raks.pvcreator.presentation.screen.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.*
import com.raks.pvcreator.R
import com.raks.pvcreator.ui.MainViewModel
import com.raks.pvcreator.ui.theme.icons.RaksLogo
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: MainViewModel = hiltViewModel(LocalView.current.findViewTreeViewModelStoreOwner()!!)
) {
    val state = viewModel.state.value!!


    val scale = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 500,
                easing = { OvershootInterpolator(2f).getInterpolation(it) }
            )
        )
        delay(3000L)
    }

    val theone = if (state.isDarkTheme) R.raw.pv_background_dark else R.raw.pv_background_light

    val compositionBack         by rememberLottieComposition(LottieCompositionSpec.RawRes(theone))
    val progressBack            by animateLottieCompositionAsState(
        composition = compositionBack,
        iterations = LottieConstants.IterateForever,
    )


    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = compositionBack,
            progress    = { progressBack },
            contentScale = ContentScale.FillBounds,
            modifier    = Modifier.fillMaxSize()
        )
//        PlasmaBackground(
//            colors = arrayOf(
//                MaterialTheme.colorScheme.surface,
//                MaterialTheme.colorScheme.onSurface,
//                MaterialTheme.colorScheme.surfaceVariant,
//                MaterialTheme.colorScheme.onSurfaceVariant,
//                MaterialTheme.colorScheme.surfaceTint,
//            ),
//        )
        Image(
            painter = painterResource(R.drawable.pv_splash_light),
            contentDescription = stringResource(R.string.label_pvcard),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize(),
        )
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Icon(
                imageVector = RaksLogo,
                contentDescription = "Logo Icon",
                tint = MaterialTheme.colorScheme.inversePrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            )
        }
    }
}

