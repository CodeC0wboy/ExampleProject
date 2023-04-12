package com.dnc.buendeal.core.extentions

import android.content.Context
import androidx.core.view.WindowInsetsCompat

fun WindowInsetsCompat.isKeyboardInset(context: Context): Boolean =
    systemWindowInsets.bottom > context.dpToPx(100f)
