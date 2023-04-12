package com.dnc.buendeal.core.core.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.databinding.ViewAddProductLanguageSelectorBinding

class AddProductLanguageSelector @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs) {
    private val binding =
        ViewAddProductLanguageSelectorBinding.inflate(LayoutInflater.from(context), this, true)

    private val list = listOf(
        binding.btnEsp,
        binding.btnEng
    )
    private var currentActiveTab = LanguageTabs.ESP
    var onEspSelectorClick: (() -> Unit)? = null
    var onEngSelectorClick: (() -> Unit)? = null

    init {
        setActiveLanguage(currentActiveTab)

        binding.btnEsp.setOnClickListener {
            setActiveLanguage(LanguageTabs.ESP)
            onEspSelectorClick?.invoke()
        }

        binding.btnEng.setOnClickListener {
            setActiveLanguage(LanguageTabs.ENG)
            onEngSelectorClick?.invoke()
        }
    }

    private fun setActiveLanguage(activeItem: LanguageTabs) {
        currentActiveTab = activeItem
        list.forEach {
            if (it.id == activeItem.viewId) {
                it.setBackgroundResource(activeItem.bgActive)
            } else {
                it.setBackgroundResource(activeItem.bgDefault)
            }
        }
    }

    enum class LanguageTabs(
        val viewId: Int,
        val title: Int,
        val bgDefault: Int,
        val bgActive: Int,
    ) {
        ESP(
            R.id.btnEsp,
            R.string.esp,
            R.drawable.bg_default_add_product_language_selector_right,
            R.drawable.bg_selected_add_product_language_selector_left,
        ),
        ENG(
            R.id.btnEng,
            R.string.eng,
            R.drawable.bg_default_add_product_language_selector_left,
            R.drawable.bg_selected_add_product_language_selector_right,
        )
    }
}
