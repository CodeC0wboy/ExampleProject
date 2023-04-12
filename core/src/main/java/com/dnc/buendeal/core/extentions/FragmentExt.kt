package com.dnc.buendeal.core.extentions

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ScrollView
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.annotation.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ViewModelOwner

const val ARG_PARAMS = "params"

fun Fragment.dimen(@DimenRes dimension: Int): Int {
    return resources.getDimensionPixelOffset(dimension)
}

fun Fragment.dimenF(@DimenRes dimension: Int): Float =
    resources.getDimension(dimension)

/**
 * @param ignoreNavHostFragment should function return [NavHostFragment] if fragment's parent is NavHostFragment
 */
fun Fragment.getParentFragment(ignoreNavHostFragment: Boolean): Fragment? =
    parentFragment?.let { fragment ->
        if (fragment is NavHostFragment && ignoreNavHostFragment) {
            fragment.getParentFragment(true)
        } else {
            fragment
        }
    }

fun Fragment.color(@ColorRes colorRes: Int) = ContextCompat.getColor(requireContext(), colorRes)

fun Fragment.drawable(@DrawableRes drawableRes: Int) =
    ContextCompat.getDrawable(requireContext(), drawableRes)

fun Fragment.getParentFragmentByClass(classType: Class<out Fragment>): Fragment {
    var parent = requireParentFragment()
    while (parent::class.java != classType) {
        parent = parent.requireParentFragment()
    }
    return parent
}

inline fun <reified T> Fragment.getParentFragmentByClassOrNull(): T? {
    var parent = parentFragment
    while (parent != null) {
        if (parent is T) {
            return parent
        }

        parent = parent.parentFragment
    }

    return null
}

fun Fragment.getNotHostParentFragment(): Fragment {
    var parent = requireParentFragment()
    while (parent is NavHostFragment) {
        parent = parent.requireParentFragment()
    }
    return parent
}

fun Fragment.requestNotHostParentFragment(): Fragment? {
    var parent = parentFragment
    while (parent is NavHostFragment) {
        parent = parent.parentFragment
    }
    return parent
}

fun Fragment.hideKeyboard() {
    val view = this.activity?.currentFocus ?: this.view?.rootView
    view?.also {
        val imm = it.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun Fragment.showKeyboard(view: View?) {
    val imm =
        ContextCompat.getSystemService(requireContext(), InputMethodManager::class.java)
    imm?.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}

fun Fragment.replaceFragment(
    fragment: Fragment,
    @IdRes containerId: Int,
    tag: String? = null,
    animate: Boolean = true,
    @AnimatorRes @AnimRes enter: Int = android.R.anim.fade_in,
    @AnimatorRes @AnimRes exit: Int = android.R.anim.fade_out
) = childFragmentManager.replaceFragment(
    fragment,
    containerId,
    tag,
    enter = enter,
    exit = exit,
    animate = animate
)

fun <T> Fragment.getResultLiveData(key: String): LiveData<T>? {
    return findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData(key)
}

fun <T> Fragment.getResultAndNullifyLiveData(key: String): LiveData<T>? {
    val liveData = findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)
    liveData?.value = null // cause of multiple triggering
    return liveData
}

fun <T> Fragment.setResult(key: String, result: T) {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key, result)
}

fun <T> Fragment.setResultToDestination(@IdRes destinationId: Int, key: String, result: T) {
    findNavController().getBackStackEntry(destinationId).savedStateHandle.set(key, result)
}

inline fun Fragment.onBackPressedCallback(crossinline onBackPressedCallback: OnBackPressedCallback.() -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(this) {
        onBackPressedCallback()
    }
}

fun Fragment.asOwnerDefinition(): () -> ViewModelOwner = { ViewModelOwner.from(this, this) }

fun Fragment.putArgs(params: Parcelable) {
    arguments = Bundle().apply { putParcelable(ARG_PARAMS, params) }
}

inline fun <reified T : Parcelable> Fragment.getArgs(): T? =
    arguments?.getParcelable(ARG_PARAMS)

fun NavHostFragment.getCurrentFragment(): Fragment? = childFragmentManager.primaryNavigationFragment

fun Fragment.setupUpButtonVisibilityListener(recycler: RecyclerView, button: FloatingActionButton) {
    var sum = 0

    recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val height = recycler.height

            sum += dy

            if (sum >= height / 2) button.show()
            else button.hide()
        }
    })
}

fun Fragment.setupUpButtonVisibilityListener(scrollView: ScrollView, button: FloatingActionButton) {

    scrollView.viewTreeObserver.addOnScrollChangedListener {
        val height = scrollView.height

        if (scrollView.scrollY >= height / 2) button.show()
        else button.hide()
    }
}
