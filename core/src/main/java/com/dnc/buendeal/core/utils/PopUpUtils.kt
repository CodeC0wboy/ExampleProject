package com.dnc.buendeal.core.utils

import android.content.Context
import android.view.WindowManager
import android.widget.PopupWindow

object PopUpUtils {

    fun PopupWindow.dimBehind() {
        val container = contentView.rootView
        val context = contentView.context
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val p = container.layoutParams as WindowManager.LayoutParams
        p.flags = p.flags or WindowManager.LayoutParams.FLAG_DIM_BEHIND
        p.dimAmount = 0.6f
        wm.updateViewLayout(container, p)
    }
}
