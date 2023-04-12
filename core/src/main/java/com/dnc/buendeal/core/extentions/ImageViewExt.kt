package com.dnc.buendeal.core.extentions

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.core.domain.data.Image

fun ImageView.loadImage(url: String?, placeHolder: Int? = R.mipmap.ic_launcher_round) {
    if (url?.isBlank() == true) return
    Glide.with(this)
        .load(url)
        .apply {
            if (placeHolder != null) placeholder(placeHolder)
        }
        .into(this)
}

fun ImageView.loadImage(uri: Uri?, placeHolder: Int? = R.mipmap.ic_launcher_round) {
    Glide.with(this)
        .load(uri)
        .apply {
            if (placeHolder != null) placeholder(placeHolder)
        }
        .into(this)
}

fun ImageView.loadImage(
    image: Bitmap?,
    placeHolder: Int? = R.mipmap.ic_launcher_round,
    resourceReady: (Drawable) -> Unit
) {
    Glide.with(this)
        .load(image)
        .apply {
            if (placeHolder != null) placeholder(placeHolder)
        }
        .addListener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return true
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                resource?.also { resourceReady.invoke(it) }
                return true
            }
        })
        .into(this)
}

fun ImageView.loadImage(
    image: Image,
    placeHolder: Int? = R.mipmap.ic_launcher_round
) {
    val glide = Glide.with(this)
    val builder = when (image) {
        is Image.ImgRes -> glide.load(image.resId)
        is Image.ImgUrl -> glide.load(image.url)
    }
    placeHolder?.also {
        builder.placeholder(it)
    }
    builder.into(this)
}
