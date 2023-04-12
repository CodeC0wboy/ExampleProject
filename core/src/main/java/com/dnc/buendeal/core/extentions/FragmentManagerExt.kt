package com.dnc.buendeal.core.extentions

import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun FragmentManager.transaction(
    commitNow: Boolean = false,
    operation: FragmentTransaction.() -> Unit
) {
    val fragmentTransaction = beginTransaction()
    operation(fragmentTransaction)

    if (commitNow) {
        fragmentTransaction.commitNow()
    } else {
        fragmentTransaction.commit()
    }
}

fun FragmentManager.replaceFragment(
    fragment: Fragment,
    @IdRes containerId: Int,
    tag: String? = null,
    animate: Boolean = true,
    commitNow: Boolean = true,
    @AnimatorRes @AnimRes enter: Int = android.R.anim.fade_in,
    @AnimatorRes @AnimRes exit: Int = android.R.anim.fade_out
) {
    transaction(commitNow) {
        if (animate) setCustomAnimations(enter, exit)
        replace(containerId, fragment, tag)
    }
}
