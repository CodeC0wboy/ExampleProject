package com.dnc.buendeal.core.core.ui.view

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText

class DisabledTextInputEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextInputEditText(context, attrs, defStyleAttr) {

    init {
        isCursorVisible = false
        isLongClickable = false
        isFocusable = false
        isSelected = false
        keyListener = null
        setBackgroundResource(android.R.color.transparent)
    }
}
