package com.dnc.buendeal.core.core.ui.view

import android.content.Context
import android.text.Html
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.HtmlCompat
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.databinding.TextAreaInputLayoutBinding
import com.dnc.buendeal.core.extentions.addClearOnChangeTextWatcher
import com.dnc.buendeal.core.extentions.enable
import com.dnc.buendeal.core.extentions.getAttributes

class TextAreaTextInputLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs) {

    private val binding: TextAreaInputLayoutBinding =
        TextAreaInputLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val enabled = attrs?.getAttributeBooleanValue(nameSpace, enabled, true)

        enable(enabled ?: true)

        getAttributes(attrs, R.styleable.inputTxtLayout) {
            val textLabel = getString(R.styleable.inputTxtLayout_label)
            if (textLabel != null) {
                binding.textInputLayoutTextArea.hint =
                    Html.fromHtml(textLabel, HtmlCompat.FROM_HTML_MODE_LEGACY)
            }
        }

        binding.textInputLayoutTextArea.addClearOnChangeTextWatcher(android.R.color.transparent)
    }

    fun setError(error: String) {
        with(binding) {
            textInputLayoutTextArea.error = error
            textInputLayoutTextArea.errorIconDrawable = null
        }
    }

    fun setLabel(label: String) {
        binding.textInputLayoutTextArea.hint = label
    }

    companion object {
        private const val nameSpace = "http://schemas.android.com/apk/res/android"
        private const val enabled = "enabled"
    }
}
