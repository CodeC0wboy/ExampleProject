package com.dnc.buendeal.core.core.ui.view

import android.content.Context
import android.text.Html
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.databinding.DropdownMenuTextInputLayoutBinding
import com.dnc.buendeal.core.extentions.enable
import com.dnc.buendeal.core.extentions.getAttributes

class DropdownMenuTextInputLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : FrameLayout(context, attrs) {
    private val binding: DropdownMenuTextInputLayoutBinding =
        DropdownMenuTextInputLayoutBinding.inflate(LayoutInflater.from(context), this)

    init {
        val enabled = attrs?.getAttributeBooleanValue(nameSpace, enabled, true)

        enable(enabled ?: true)

        binding.dropdownTextInputLayout.setEndIconDrawable(R.drawable.dropdown_menu_icon_selector)

        getAttributes(attrs, R.styleable.inputTxtLayout) {
            val textLabel = getString(R.styleable.inputTxtLayout_label)
            if (textLabel != null) {
                binding.dropdownTextInputLayout.hint =
                    Html.fromHtml(textLabel, HtmlCompat.FROM_HTML_MODE_LEGACY)
            } else {
                binding.dropdownTextInputLayout.setBoxBackgroundColorResource(R.color.light_gray_3)
            }
            val textHint = getString(R.styleable.inputTxtLayout_hint)

            binding.autoCompleteTextView.setText(textHint)
            binding.autoCompleteTextView.setTextColor(ContextCompat.getColor(context, R.color.dark_gray_2))
        }
    }

    fun setupAdapter(adapter: ArrayAdapter<String>) {
        binding.autoCompleteTextView.setAdapter(adapter)
    }

    fun setupObjectAdapter(adapter: ArrayAdapter<ItemModel>) {
        binding.autoCompleteTextView.setAdapter(adapter)
    }

    fun setupTestAdapter() {
        val numbersOfAnswers = resources.getStringArray(R.array.number_of_answers)
        val arrayAdapter = ArrayAdapter(context, R.layout.dropdown_item, numbersOfAnswers)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
    }

    fun setHint(hint: String) {
        binding.autoCompleteTextView.setText(hint)
    }

    fun setLabel(label: String) {
        binding.dropdownTextInputLayout.hint = label
    }

    fun setonItemClickListener(listener: AdapterView.OnItemClickListener) {
        binding.autoCompleteTextView.onItemClickListener = listener
    }

    companion object {
        private const val nameSpace = "http://schemas.android.com/apk/res/android"
        private const val enabled = "enabled"
    }
}
