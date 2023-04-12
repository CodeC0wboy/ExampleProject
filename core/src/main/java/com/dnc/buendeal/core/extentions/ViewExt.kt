package com.dnc.buendeal.core.extentions

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.ClipData
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Path
import android.graphics.Point
import android.graphics.PointF
import android.graphics.Rect
import android.net.Uri
import android.os.Build
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.view.ViewTreeObserver
import android.view.animation.*
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.annotation.*
import androidx.appcompat.widget.Toolbar
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.animation.addListener
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import androidx.core.view.isVisible
import com.dnc.buendeal.core.R
import kotlin.math.abs

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

inline fun View.getAttributes(
    set: AttributeSet?,
    attrs: IntArray,
    defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0,
    crossinline action: TypedArray.() -> Unit
) {
    set ?: return
    val array = context.obtainStyledAttributes(set, attrs, defStyleAttr, defStyleRes)
    try {
        array.action()
    } finally {
        array.recycle()
    }
}

fun View.inScreenBounds(): Boolean {
    if (!isShown) {
        return false
    }
    val actualPosition = Rect()
    getGlobalVisibleRect(actualPosition)
    val screen =
        Rect(0, 0, resources.displayMetrics.widthPixels, resources.displayMetrics.heightPixels)
    return Rect.intersects(actualPosition, screen)
}

fun View.intersectsWith(view: View): Boolean {
    if (!isShown) {
        return false
    }
    val currentViewRect = Rect()
    getGlobalVisibleRect(currentViewRect)
    val anotherViewRect = Rect()
    view.getGlobalVisibleRect(anotherViewRect)
    return Rect.intersects(currentViewRect, anotherViewRect)
}

fun View.startXmlAnimation(
    @AnimatorRes @AnimRes id: Int,
    reversed: Boolean = false,
    onEnd: () -> Unit = {}
) {
    startAnimation(
        AnimationUtils.loadAnimation(context, id).apply {
            if (reversed) interpolator = Interpolator { input -> abs(input - 1f) }
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    onEnd()
                }

                override fun onAnimationStart(animation: Animation?) {
                }
            })
        }
    )
}

fun View.moveToInitialPosition(initialX: Float, initialY: Float) {
    val path = Path().apply {
        moveTo(this@moveToInitialPosition.x, this@moveToInitialPosition.y)
        lineTo(initialX, initialY)
    }

    ObjectAnimator.ofFloat(this, View.X, View.Y, path).apply {
        duration = 200L
        interpolator = LinearInterpolator()
        start()
    }
}

fun View.resizeAndGoneAnimation(animationDuration: Long = 100, onEnd: () -> Unit = {}) {
    AnimatorSet().apply {
        val scaleY =
            ObjectAnimator.ofFloat(this@resizeAndGoneAnimation, View.SCALE_Y, 1f, 0.1f).apply {
                duration = animationDuration
            }
        val scaleX =
            ObjectAnimator.ofFloat(this@resizeAndGoneAnimation, View.SCALE_X, 1f, 0.1f).apply {
                duration = animationDuration
            }
        playTogether(scaleX, scaleY)

        addListener(onEnd = {
            isEnabled = false
            ObjectAnimator.ofFloat(this@resizeAndGoneAnimation, View.ALPHA, 1f, 0f).apply {
                duration = 100
                addListener(onEnd = { onEnd() })
                start()
            }
        })
        start()
    }
}

fun View.runScaleAnimation(animDuration: Long, from: Float, to: Float, onEnd: () -> Unit = {}) {
    AnimatorSet().apply {
        playTogether(
            ObjectAnimator.ofFloat(this@runScaleAnimation, View.SCALE_X, from, to),
            ObjectAnimator.ofFloat(this@runScaleAnimation, View.SCALE_Y, from, to)
        )
        duration = animDuration
        doOnEnd { onEnd() }
    }.start()
}

fun View.startDragEvent() {
    val data = ClipData.newPlainText("", "")
    val shadowBuilder = View.DragShadowBuilder(this)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        startDragAndDrop(data, shadowBuilder, this, 0)
    else startDrag(data, shadowBuilder, this, 0)

    invisible()
}

fun View.shake() {
    ObjectAnimator
        .ofFloat(this, "translationX", 0f, 25f, -25f, 21f, -21f, 15f, -15f, 6f, -6f, 0f)
        .apply { interpolator = AccelerateInterpolator() }
        .setDuration(400)
        .start()
}

fun View.doOnApplyWindowInsets(block: (View, insets: WindowInsetsCompat, initialPadding: Rect) -> WindowInsetsCompat) {
    doOnApplyWindowInsets(this, block)
}

fun View.doOnApplyWindowInsets(
    targetView: View,
    block: (View, insets: WindowInsetsCompat, initialPadding: Rect) -> WindowInsetsCompat
) {
    val initialPadding = recordInitialPaddingForView(targetView)
    ViewCompat.setOnApplyWindowInsetsListener(this) { _, insets ->
        block(targetView, insets, initialPadding)
    }
    requestApplyInsetsWhenAttached()
}

private fun recordInitialPaddingForView(view: View) =
    Rect(view.paddingLeft, view.paddingTop, view.paddingRight, view.paddingBottom)

fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        ViewCompat.requestApplyInsets(this)
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                v.removeOnAttachStateChangeListener(this)
                ViewCompat.requestApplyInsets(v)
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}

fun View.hideKeyboard() {
    val view = this.rootView
    view?.also {
        val imm = it.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun View.htmlFormat(@StringRes resId: Int, vararg args: Any?): CharSequence {
    return HtmlCompat.fromHtml(
        String.format(resources.getString(resId), *args),
        HtmlCompat.FROM_HTML_MODE_COMPACT
    )
}

fun View.animateAlpha(toAlpha: Float, duration: Long = 100) {
    animate()
        .alpha(toAlpha).duration = duration
}

fun View.resIdFromAttr(@AttrRes attr: Int): Int =
    TypedValue().apply { context.theme.resolveAttribute(attr, this, true) }.resourceId

fun View.getVisibleHeightPercentage(): Int {
    val itemRect = Rect()
    val isParentViewEmpty = getLocalVisibleRect(itemRect)
    val visibleHeight = itemRect.height().toDouble()
    val height = measuredHeight
    val viewVisibleHeightPercentage = visibleHeight / height * 100
    return if (isParentViewEmpty) {
        viewVisibleHeightPercentage.toInt()
    } else {
        0
    }
}

fun ViewGroup.findToolbar(): Toolbar? {
    if (this is Toolbar) {
        return this
    }

    for (i in 0 until childCount) {
        (getChildAt(i) as? ViewGroup)?.also {
            val childViewGroup = it.findToolbar()
            if (childViewGroup is Toolbar) {
                return childViewGroup
            }
        }
    }
    return null
}

inline fun <reified T : ViewGroup> View.findFirstParentByClassOrNull(): T? {
    var parent = this.parent
    while (parent !is T) {
        if (parent == null) {
            return null
        }
        parent = parent.parent
    }
    return parent
}

fun View.applyAnotherViewSize(anotherView: View) {
    layoutParams = layoutParams?.apply {
        width = anotherView.width
        height = anotherView.height
    } ?: ViewGroup.LayoutParams(anotherView.width, anotherView.height)
}

fun View.applyNewViewSize(newWidth: Int, newHeight: Int? = null) {
    layoutParams = layoutParams?.apply {
        width = newWidth
        height = newHeight ?: newWidth
    } ?: ViewGroup.LayoutParams(newWidth, newHeight ?: newWidth)
}

fun View.applyAnotherViewLocation(anotherView: View) {
    val location = anotherView.getScreenLocation()
    x = location.first().toFloat()
    y = location.last().toFloat()
}

@Deprecated("use [screenPosition] or [screenPositionF]")
fun View.getScreenLocation(location: IntArray = IntArray(2)): IntArray {
    getLocationOnScreen(location)
    return location
}

private val sharedIntArray = IntArray(2)
val View.screenPosition: Point
    get() {
        val (x, y) = getScreenLocation(sharedIntArray)
        return Point(x, y)
    }

val View.screenPositionF: PointF
    get() {
        val (x, y) = getScreenLocation(sharedIntArray)
        return PointF(x.toFloat(), y.toFloat())
    }

fun View.hideWithAlphaAnimation(duration: Long = 200) {
    animate().apply {
        alpha(0f)
        this.duration = duration
        withEndAction {
            isVisible = false
        }
        start()
    }
}

fun View.showWithAlphaAnimation(duration: Long = 200) {
    animate().apply {
        alpha(1f)
        this.duration = duration
        withStartAction {
            isVisible = true
        }
        start()
    }
}

/**
 * https://developer.squareup.com/blog/showing-the-android-keyboard-reliably/
 *
 */
fun View.focusAndShowKeyboard() {
    /**
     * This is to be called when the window already has focus.
     */
    fun View.showTheKeyboardNow() {
        if (isFocused) {
            post {
                // We still post the call, just in case we are being notified of the windows focus
                // but InputMethodManager didn't get properly setup yet.
                val imm =
                    context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
            }
        }
    }

    requestFocus()
    if (hasWindowFocus()) {
        // No need to wait for the window to get focus.
        showTheKeyboardNow()
    } else {
        // We need to wait until the window gets focus.
        viewTreeObserver.addOnWindowFocusChangeListener(
            object : ViewTreeObserver.OnWindowFocusChangeListener {
                override fun onWindowFocusChanged(hasFocus: Boolean) {
                    // This notification will arrive just before the InputMethodManager gets set up.
                    if (hasFocus) {
                        this@focusAndShowKeyboard.showTheKeyboardNow()
                        // It’s very important to remove this listener once we are done.
                        viewTreeObserver.removeOnWindowFocusChangeListener(this)
                    }
                }
            })
    }
}

fun View.getTopParent(): ViewGroup? {
    var iParent = this.parent as? ViewGroup

    while (iParent?.parent as? ViewGroup != null) {
        iParent = iParent.parent as ViewGroup
    }

    return iParent
}

fun View.getDeepChildOffset(
    mainParent: ViewGroup,
) = Point().also {
    getDeepChildOffset(mainParent, parent, this, it)
}

/**
 * https://stackoverflow.com/questions/21483188/scroll-to-a-specific-view-in-scroll-view
 *
 * Used to get deep child offset.
 *
 *
 * 1. We need to scroll to child in scrollview, but the child may not the direct child to scrollview.
 * 2. So to get correct child position to scroll, we need to iterate through all of its parent views till the main parent.
 *
 * @param mainParent        Main Top parent.
 * @param parent            Parent.
 * @param child             Child.
 * @param accumulatedOffset Accumulated Offset.
 */
private tailrec fun getDeepChildOffset(
    mainParent: ViewGroup,
    parent: ViewParent,
    child: View,
    accumulatedOffset: Point
) {
    val parentGroup = parent as ViewGroup
    accumulatedOffset.x += child.left
    accumulatedOffset.y += child.top
    if (parentGroup == mainParent) {
        return
    }
    getDeepChildOffset(mainParent, parentGroup.parent, parentGroup, accumulatedOffset)
}

fun (() -> Unit).toClickListener(): View.OnClickListener {
    return View.OnClickListener { this() }
}

fun View.enable(enabled: Boolean, view: View = this) {
    (view as? ViewGroup)?.children?.forEach {
        it.isEnabled = enabled
        enable(enabled, it as? ViewGroup ?: return)
    }
}

fun TextView.makeLinks(vararg links: Pair<String, View.OnClickListener>) {
    val spannableString = SpannableString(this.text)
    var startIndexOfLink = -1
    for (link in links) {
        val clickableSpan = object : ClickableSpan() {
            override fun updateDrawState(textPaint: TextPaint) {
                textPaint.color = ContextCompat.getColor(context, R.color.main_blue)
                textPaint.isUnderlineText = false
            }

            override fun onClick(view: View) {
                Selection.setSelection((view as TextView).text as Spannable, 0)
                view.invalidate()
                link.second.onClick(view)
            }
        }
        startIndexOfLink = this.text.toString().indexOf(link.first, startIndexOfLink + 1)
        spannableString.setSpan(
            clickableSpan, startIndexOfLink, startIndexOfLink + link.first.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    this.movementMethod = LinkMovementMethod.getInstance()
    this.setText(spannableString, TextView.BufferType.SPANNABLE)
}

fun View.openCustomTab(url: String) {
    val customTabsIntent = CustomTabsIntent.Builder()
        .setShowTitle(true)
        .build()

    customTabsIntent.launchUrl(context, Uri.parse(url))
}
