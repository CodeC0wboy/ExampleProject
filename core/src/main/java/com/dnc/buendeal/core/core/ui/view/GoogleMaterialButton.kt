package com.dnc.buendeal.core.core.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.dnc.buendeal.core.R
import com.google.android.material.button.MaterialButton

class GoogleMaterialButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : MaterialButton(context, attrs) {
    private val googleDrawable by lazy {
        ContextCompat.getDrawable(context, R.drawable.ic_google_logo)?.apply {
            colorFilter =
                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    ContextCompat.getColor(context, R.color.red),
                    BlendModeCompat.DST
                )
        }
    }

    init {
        icon = googleDrawable
    }
}
