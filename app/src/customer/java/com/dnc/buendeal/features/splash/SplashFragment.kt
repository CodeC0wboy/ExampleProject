package com.dnc.buendeal.features.splash

import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import com.dnc.buendeal.R
import com.dnc.buendeal.core.extentions.doOnApplyWindowInsets

private const val ANIM_DURATION = 800L

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var onAnimationEndListener: () -> Unit
    private var topInsetPixels = 0

    private var container: ConstraintLayout? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        container = view.findViewById(R.id.container)
        view.doOnApplyWindowInsets { _, insets, _ ->
            insets.also {
                topInsetPixels = it.systemWindowInsetTop
            }
        }
    }

    fun startSplashAnimation(listener: () -> Unit) {
        onAnimationEndListener = listener
        container?.doOnLayout {
            it.animate()
                .alpha(0f)
                .setDuration(ANIM_DURATION)
                .setInterpolator(AccelerateInterpolator())
                .withEndAction {
                    onAnimationEndListener.invoke()
                }
                .start()
        }
    }
}
