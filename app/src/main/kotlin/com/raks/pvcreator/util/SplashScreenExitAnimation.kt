package com.raks.pvcreator.util

import android.animation.ObjectAnimator
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.BaseInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreenViewProvider
import kotlin.math.max

internal fun splashScreenExitAnimation(
    splashScreenView:   SplashScreenViewProvider,
    splashDuration:     Long                     = 400L,
    splashInterpolator: BaseInterpolator         = AccelerateDecelerateInterpolator(),
) {
    val circularReveal = ViewAnimationUtils.createCircularReveal(
        splashScreenView.view,
        splashScreenView.view.width / 2,
        splashScreenView.view.height / 2,
        max(splashScreenView.view.width, splashScreenView.view.height).toFloat(),
        0f,
    ).apply {
        duration     = splashDuration
        interpolator = splashInterpolator
        doOnEnd { splashScreenView.remove() }
    }

    val scaleX = ObjectAnimator.ofFloat(
        splashScreenView.iconView,
        View.SCALE_X,
        1.0f,
        0.0f
    ).apply {
        duration     = splashDuration
        interpolator = splashInterpolator
        doOnEnd { splashScreenView.remove() }
    }

    val scaleY = ObjectAnimator.ofFloat(
        splashScreenView.iconView,
        View.SCALE_Y,
        1.0f,
        0.0f
    ).apply {
        duration     = splashDuration
        interpolator = splashInterpolator
        doOnEnd { splashScreenView.remove() }
    }

    circularReveal.start()
    scaleX.start()
    scaleY.start()
}
