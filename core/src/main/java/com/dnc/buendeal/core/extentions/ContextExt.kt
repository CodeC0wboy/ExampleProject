package com.dnc.buendeal.core.extentions

import android.content.ClipboardManager
import android.content.Context
import android.graphics.drawable.Drawable
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService

fun Context.getDrawableId(name: String): Int? =
    resources.getIdentifier(name, "drawable", packageName)

fun Context.resDrawableArray(namePrefix: String, framesLength: Int): List<Drawable> {
    val drawables = mutableListOf<Drawable>()
    for (i in 0 until framesLength) {
        getDrawableId(namePrefix + "_" + i)?.let { id ->
            ContextCompat.getDrawable(this, id)?.let { drawable ->
                drawables.add(drawable)
            }
        }
    }
    return drawables
}

/**
 * Convert [dp] to pixels as int
 */
fun Context.dpToPx(dp: Float): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()

/**
 * Convert [dp] to pixels as float
 */
fun Context.dpToPxF(dp: Float): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)

fun Context.dimen(@DimenRes resource: Int): Int = resources.getDimensionPixelSize(resource)

private val Context.clipBoardManager
    get() = getSystemService<ClipboardManager>()

fun Context.getMediaDuration(uri: Uri): Long {
    val retriever = MediaMetadataRetriever()
    retriever.setDataSource(this, uri)
    val duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
    retriever.release()

    return duration?.toLongOrNull() ?: 0
}

fun Context.runOnUiThread(f: Context.() -> Unit) {
    if (Looper.getMainLooper() === Looper.myLooper()) f() else Handler(Looper.getMainLooper()).post { f() }
}
