package com.dnc.buendeal.core.core.ui.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.databinding.ViewChipTextInputLayoutBinding
import com.dnc.buendeal.core.extentions.dp
import com.dnc.buendeal.core.extentions.enable
import com.google.android.material.chip.Chip
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel

class ChipTextInputLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs) {

    private val binding: ViewChipTextInputLayoutBinding =
        ViewChipTextInputLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    val listKeywords = mutableListOf<String>()

    var keywordsCallback: ((List<String>) -> Unit)? = null

    init {

        val enabled = attrs?.getAttributeBooleanValue(nameSpace, enabled, true)
        enable(enabled ?: true)
        binding.horizontalScrollView.smoothScrollTo(binding.textInputEditTextInner.right, 0)

        binding.textInputEditTextInner.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                val shapeAppearance = ShapeAppearanceModel().toBuilder().setAllCorners(
                    CornerFamily.ROUNDED, 4.dp.toFloat()
                )
                val keyword = s.toString()
                if (keyword.length > 1 && keyword.endsWith(" ")) {
                    val chip = Chip(context)

                    chip.apply {
                        text = keyword.substring(0, keyword.length - 1)
                        chipMinHeight = 29.dp.toFloat()
                        shapeAppearanceModel = shapeAppearance.build()
                        setTextAppearance(R.style.InterRegular_CaptureInterRegular12)
                        isCloseIconVisible = true
                        closeIconSize = 10.dp.toFloat()
                        setCloseIconResource(R.drawable.ic_cross_chip)
                        setChipBackgroundColorResource(R.color.light_blue_1)
                    }

                    chip.setOnCloseIconClickListener {
                        val index = binding.chipGroup.indexOfChild(chip)
                        binding.chipGroup.removeView(chip)
                        listKeywords.removeAt(index)
                    }

                    binding.chipGroup.addView(chip)
                    listKeywords.add(chip.text.toString())
                    s?.clear()
                }

                focusOnView()
                keywordsCallback?.invoke(listKeywords)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.textInputEditTextInner.setOnKeyListener { _, _, event ->
            if (event != null && event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_DEL) {
                if (binding.textInputEditTextInner.length() == 0 && binding.chipGroup.childCount > 0) {
                    val chip =
                        binding.chipGroup.getChildAt(binding.chipGroup.childCount - 1) as Chip
                    val index = binding.chipGroup.indexOfChild(chip)
                    binding.chipGroup.removeView(chip)
                    listKeywords.removeAt(index)
                    keywordsCallback?.invoke(listKeywords)
                }
            }
            false
        }
    }

    private fun focusOnView() {
        binding.horizontalScrollView.post {
            binding.horizontalScrollView.scrollTo(
                binding.textInputEditTextInner.right,
                0
            )
        }
    }

    companion object {
        private const val nameSpace = "http://schemas.android.com/apk/res/android"
        private const val enabled = "enabled"
    }
}
