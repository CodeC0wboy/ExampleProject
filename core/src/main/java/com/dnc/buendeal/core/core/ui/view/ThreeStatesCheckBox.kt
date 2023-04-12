package com.dnc.buendeal.core.core.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox
import com.dnc.buendeal.core.R

class ThreeStatesCheckBox @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatCheckBox(context, attrs) {
    private var state: CheckboxStates

    init {
        state = CheckboxStates.UNCHECKED
        updateBtn()
        setOnCheckedChangeListener { _, _ ->
            state = when (state) {
                CheckboxStates.UNCHECKED -> CheckboxStates.INDETERMINATE
                CheckboxStates.INDETERMINATE -> CheckboxStates.CHECKED
                CheckboxStates.CHECKED -> CheckboxStates.UNCHECKED
            }
            updateBtn()
        }
    }

    private fun updateBtn() {
        val btnDrawable = when (state) {
            CheckboxStates.INDETERMINATE -> R.drawable.ic_checkbox_indeterminate
            CheckboxStates.UNCHECKED -> R.drawable.ic_checkbox_unchecked
            CheckboxStates.CHECKED -> R.drawable.ic_checkbox_checked
        }
        setButtonDrawable(btnDrawable)
    }

    fun getState(): CheckboxStates {
        return state
    }

    fun setState(state: CheckboxStates) {
        this.state = state
        updateBtn()
    }

    enum class CheckboxStates {
        UNCHECKED, INDETERMINATE, CHECKED
    }
}
