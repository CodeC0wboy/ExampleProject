package com.dnc.buendeal.core.core.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.doOnLayout
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.databinding.ViewBottomReviewsScreenBinding
import com.dnc.buendeal.core.extentions.getAttributes

class BottomReviewsQuestionsScreen @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs) {
    private val binding: ViewBottomReviewsScreenBinding =
        ViewBottomReviewsScreenBinding.inflate(LayoutInflater.from(context), this, true)

    var onBtnClick: (() -> Unit)? = null

    init {
        getAttributes(attrs, R.styleable.bottomBtn) {
            val textBtn = getString(R.styleable.bottomBtn_btnText)
            binding.reviewsQuestionsBtn.text = textBtn

            val hintSort = getString(R.styleable.bottomBtn_sortHint)
            hintSort?.let { binding.reviewsDropdownMenuSort.setHint(it) }
        }

        binding.reviewsQuestionsBtn.setOnClickListener {
            onBtnClick?.invoke()
        }

        doOnLayout {
            (parent as? ViewGroup)?.clipChildren = false
            clipChildren = false
        }
    }

    fun setUpAdapter(list: List<String>) {
        val adapter = ArrayAdapter(
            binding.root.context,
            R.layout.dropdown_item,
            list
        )
        binding.reviewsDropdownMenuSort.setupAdapter(adapter)
    }
}
