package com.raks.pvcreator.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.*
import com.raks.pvcreator.R
import com.raks.pvcreator.presentation.components.LocalTheme

@Composable
fun SplashAnimation() {
    val raw       = if (LocalTheme.current) R.raw.pv_background_dark else R.raw.pv_background_light
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(raw))
    val progress    by animateLottieCompositionAsState(
        composition = composition,
        iterations  = LottieConstants.IterateForever,
    )

    LottieAnimation(
        composition  = composition,
        progress     = { progress },
        contentScale = ContentScale.FillBounds,
        modifier     = Modifier.fillMaxSize(),
    )
}