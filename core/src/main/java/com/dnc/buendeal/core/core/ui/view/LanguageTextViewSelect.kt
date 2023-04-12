package com.dnc.buendeal.core.core.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.databinding.ViewLanguageSelectBinding
import com.dnc.buendeal.core.providers.PreferencesProviderImpl

class LanguageTextViewSelect @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs) {
    private val binding: ViewLanguageSelectBinding =
        ViewLanguageSelectBinding.inflate(LayoutInflater.from(context), this, true)

    private val list = listOf(
        binding.textEN,
        binding.textES
    )
    var currentActiveTab = LANGUAGE.ES
    private val preferences = PreferencesProviderImpl(context)
    var onLanguageSwitchClick: (() -> Unit)? = null

    init {
        if (preferences.getAppLanguage() == LANGUAGE.ES.locale) setActiveTab(LANGUAGE.ES)
        else setActiveTab(LANGUAGE.EN)

        binding.textEN.setOnClickListener {
            setActiveTab(LANGUAGE.EN)
            onLanguageSwitchClick?.invoke()
        }

        binding.textES.setOnClickListener {
            setActiveTab(LANGUAGE.ES)
            onLanguageSwitchClick?.invoke()
        }
    }

    private fun setActiveTab(activeItem: LANGUAGE) {
        currentActiveTab = activeItem
        list.forEach {
            if (it.id == activeItem.viewId) {
                it.setTextColor(context.getColor(activeItem.colorActive))
            } else {
                it.setTextColor(context.getColor(activeItem.colorDefault))
            }
        }
        preferences.updateLanguage(activeItem.locale)
    }

    enum class LANGUAGE(
        val viewId: Int,
        val title: Int,
        val colorDefault: Int,
        val colorActive: Int,
        val locale: String
    ) {
        EN(
            R.id.textEN,
            R.string.en,
            R.color.language_inactive_color,
            R.color.language_active_color,
            "en"
        ),
        ES(
            R.id.textES,
            R.string.es,
            R.color.language_inactive_color,
            R.color.language_active_color,
            "es"
        )
    }
}
