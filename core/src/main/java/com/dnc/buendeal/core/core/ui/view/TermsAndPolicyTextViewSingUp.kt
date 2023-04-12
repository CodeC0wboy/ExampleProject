package com.dnc.buendeal.core.core.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View.OnClickListener
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.extentions.makeLinks
import com.dnc.buendeal.core.extentions.openCustomTab

class TermsAndPolicyTextViewSingUp @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : androidx.appcompat.widget.AppCompatTextView(context, attrs) {

    init {
        text = context.getString(R.string.sing_up_terms_of_use)
        textAlignment = TEXT_ALIGNMENT_CENTER
        setTextAppearance(R.style.InterRegular_Body2InterRegular14)

        makeLinks(
            Pair(
                context.getString(R.string.terms_of_use),
                OnClickListener {
                    openCustomTab("https://gitlab.com")
                }
            ),
            Pair(
                context.getString(R.string.private_policy),
                OnClickListener {
                    openCustomTab("https://github.com")
                }
            )
        )
    }
}
