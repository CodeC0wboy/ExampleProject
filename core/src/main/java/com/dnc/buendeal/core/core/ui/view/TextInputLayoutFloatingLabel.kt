package com.dnc.buendeal.core.core.ui.view

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.text.Html
import android.text.InputType
import android.util.AttributeSet
import android.util.SparseArray
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.text.HtmlCompat
import androidx.core.view.children
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.databinding.FloatingInputTextLayoutBinding
import com.dnc.buendeal.core.extentions.addClearOnChangeTextWatcher
import com.dnc.buendeal.core.extentions.enable
import com.dnc.buendeal.core.extentions.getAttributes
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class TextInputLayoutFloatingLabel @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : FrameLayout(context, attrs) {

    private val binding: FloatingInputTextLayoutBinding =
        FloatingInputTextLayoutBinding.inflate(LayoutInflater.from(context), this)

    init {
        val enabled = attrs?.getAttributeBooleanValue(nameSpace, enabled, true)

        enable(enabled ?: true)

        getAttributes(attrs, R.styleable.inputTxtLayout) {
            val modePassword =
                this.getBoolean(R.styleable.inputTxtLayout_modePassword, false)
            with(binding) {
                if (modePassword) {
                    textInputEditText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                    textInputLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
                    textInputLayout.setEndIconDrawable(R.drawable.show_password_selector)
                } else {
                    textInputEditText.inputType = InputType.TYPE_CLASS_TEXT
                }
            }

            val textLabel = getString(R.styleable.inputTxtLayout_label)
            if (textLabel != null) {
                binding.textInputLayout.hint =
                    Html.fromHtml(textLabel, HtmlCompat.FROM_HTML_MODE_LEGACY)
            }
            binding.textInputEditText.maxLines = getInteger(R.styleable.inputTxtLayout_maxLines, 10)

            val textHint = getString(R.styleable.inputTxtLayout_hint)
            if (textHint != null) {
                binding.textInputLayout.findViewById<TextInputEditText>(R.id.textInputEditText).hint =
                    Html.fromHtml(textHint, HtmlCompat.FROM_HTML_MODE_LEGACY)
            }

            val iconSearch = getDrawable(R.styleable.inputTxtLayout_searchIcon)
            binding.textInputLayout.startIconDrawable = iconSearch
        }

        binding.textInputLayout.addClearOnChangeTextWatcher(R.color.white)
    }

    fun setError(error: String) {
        with(binding) {
            textInputLayout.error = error
            textInputLayout.errorIconDrawable = null
            textInputLayout.setBoxBackgroundColorResource(R.color.light_gray_3)
        }
    }

    fun setText(text: String) {
        binding.textInputEditText.setText(text)
    }

    fun setInputType(type: Int) {
        binding.textInputEditText.inputType = type
    }

    // We need to manually save states of children views to avoid id overlapping
    // that causes all children views to have last child state
    override fun dispatchSaveInstanceState(container: SparseArray<Parcelable>?) {
        dispatchFreezeSelfOnly(container)
    }

    override fun dispatchRestoreInstanceState(container: SparseArray<Parcelable>?) {
        dispatchThawSelfOnly(container)
    }

    override fun onSaveInstanceState(): Parcelable {
        return Bundle().apply {
            putParcelable(SUPER_STATE, super.onSaveInstanceState())
            putSparseParcelableArray(SPARSE_STATE, saveChildViewStates())
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        var newState = state
        if (newState is Bundle) {
            val childrenState = newState.getSparseParcelableArray<Parcelable>(SPARSE_STATE)
            childrenState?.let { restoreChildViewStates(it) }
            newState = newState.getParcelable(SUPER_STATE)
        }
        super.onRestoreInstanceState(newState)
    }

    private fun saveChildViewStates(): SparseArray<Parcelable> {
        val childViewStates = SparseArray<Parcelable>()
        children.forEach { child -> child.saveHierarchyState(childViewStates) }
        return childViewStates
    }

    private fun restoreChildViewStates(childViewStates: SparseArray<Parcelable>) {
        children.forEach { child -> child.restoreHierarchyState(childViewStates) }
    }

    companion object {
        private const val nameSpace = "http://schemas.android.com/apk/res/android"
        private const val enabled = "enabled"
        private const val SUPER_STATE = "superState"
        private const val SPARSE_STATE = "sparseState"
    }
}
