package com.dnc.buendeal.features.dashboard.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.AlphaAnimation
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.dnc.buendeal.databinding.FragmentGuestSnackbarBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GuestSnackBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs) {

    private val fadeOutDelay = 4000L
    private val fadeOutTime = 200L

    private val binding: FragmentGuestSnackbarBinding =
        FragmentGuestSnackbarBinding.inflate(LayoutInflater.from(context), this, true)

    var onSignUpClick: (() -> Unit)? = null

    init {
        binding.guestSnackbarSignupButton.setOnClickListener {
            onSignUpClick?.invoke()
        }
    }

    fun fadeOutAfter() {
        findViewTreeLifecycleOwner()?.lifecycleScope?.launch {

            delay(fadeOutDelay)
            val anim = AlphaAnimation(1.0f, 0.0f)
            anim.duration = fadeOutTime
            this@GuestSnackBar.startAnimation(anim)
            this@GuestSnackBar.isVisible = false
        }
    }
}
