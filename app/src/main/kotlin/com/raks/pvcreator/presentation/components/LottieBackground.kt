package com.raks.pvcreator.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.*
import com.raks.pvcreator.R
import com.raks.pvcreator.util.LocalTheme

@Composable
fun LottieBackground(
    darkTheme: Boolean = LocalTheme.current
) {
    val raw       = if (darkTheme) R.raw.pv_background_dark else R.raw.pv_background_light
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(raw))
    val progress    by animateLottieCompositionAsState(
        composition = composition,
        iterations  = LottieConstants.IterateForever,
    )

    LottieAnimation(
        composition  = composition,
        progress     = { progress },
        modifier     = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds,
    )
}
