package com.dnc.buendeal.core.extentions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity
import org.koin.androidx.viewmodel.ViewModelOwner

fun Activity.hideKeyboard(v: View? = null) {
    val view = v ?: this.currentFocus ?: findViewById(android.R.id.content)
    view?.also {
        val imm = it.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun Activity.showKeyboard(view: View? = currentFocus) {
    view?.also {
        it.requestFocus()
        it.performClick()
        val imm =
            view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, 0)
    }
}

fun Activity.getWindowHeight(): Int {
    val displayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.heightPixels
}

fun Activity.getWindowWidth(): Int {
    val displayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.widthPixels
}

fun Activity.getRootView(): View {
    return findViewById(android.R.id.content)
}

fun Activity.isKeyboardOpen(): Boolean {
    val visibleBounds = Rect()
    getRootView().getWindowVisibleDisplayFrame(visibleBounds)
    val heightDiff = getRootView().height - visibleBounds.height()
    val marginOfError = 50f.dp
    return heightDiff > marginOfError
}

fun Activity.openMailApp() {
    val intent = Intent(Intent.ACTION_MAIN)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    intent.addCategory(Intent.CATEGORY_APP_EMAIL)
    startActivity(intent)
}

fun Activity.openLink(url: String?) {
    if (url.isNullOrBlank()) return
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
}

fun FragmentActivity.asOwnerDefinition(): () -> ViewModelOwner = { ViewModelOwner.from(this, this) }
