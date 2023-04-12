package com.dnc.buendeal.core.core.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.databinding.ViewSearchTextInputLayoutBinding
import com.dnc.buendeal.core.extentions.addClearOnChangeTextWatcher
import com.dnc.buendeal.core.extentions.enable
import com.dnc.buendeal.core.extentions.getAttributes
import com.google.android.material.textfield.TextInputLayout

class SearchTextInputLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : FrameLayout(context, attrs) {

    var onSearchClick: (() -> Unit)? = null

    private val binding: ViewSearchTextInputLayoutBinding =
        ViewSearchTextInputLayoutBinding.inflate(LayoutInflater.from(context), this)

    init {
        val enabled = attrs?.getAttributeBooleanValue(
            nameSpace,
            enabled, true
        )

        enable(enabled ?: true)
        binding.searchTextInputLayout.endIconMode = TextInputLayout.END_ICON_CUSTOM
        binding.searchTextInputLayout.setEndIconDrawable(R.drawable.ic_search)

        binding.searchTextInputLayout.addClearOnChangeTextWatcher(R.color.white)

        getAttributes(attrs, R.styleable.inputTxtLayout) {

            val textHint = getString(R.styleable.inputTxtLayout_hint)
            binding.searchTextInputEditText.hint = textHint

            binding.searchTextInputEditText.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus)
                    binding.searchTextInputEditText.hint = ""
                else
                    binding.searchTextInputEditText.hint = textHint
            }

            val focusableET = getBoolean(R.styleable.inputTxtLayout_focusableETSearch, true)
            binding.searchTextInputEditText.isFocusable = focusableET
        }

        binding.searchTextInputEditText.setOnClickListener {
            onSearchClick?.invoke()
        }
    }

    fun setError(error: String) {
        with(binding) {
            searchTextInputLayout.error = error
            searchTextInputLayout.errorIconDrawable = null
            searchTextInputLayout.setBoxBackgroundColorResource(R.color.light_gray_3)
        }
    }

    companion object {
        private const val nameSpace = "http://schemas.android.com/apk/res/android"
        private const val enabled = "enabled"
    }
}
