package com.dnc.buendeal.core.extentions

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout?.addClearOnChangeTextWatcher(
    resId: Int? = null,
    errorClearedCallback: (() -> Unit)? = null
) {
    this?.editText?.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (this@addClearOnChangeTextWatcher.error != null) {
                this@addClearOnChangeTextWatcher.error = null
                resId?.let { setBoxBackgroundColorResource(it) }
                errorClearedCallback?.invoke()
            }
        }
    })
}
