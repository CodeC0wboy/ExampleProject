package com.dnc.buendeal.core.extentions

import androidx.core.text.HtmlCompat
import com.dnc.buendeal.core.BuildConfig
import com.dnc.buendeal.core.utils.Constants
import java.text.SimpleDateFormat
import java.util.*

infix fun Any.hasSameClassWith(other: Any) = javaClass.isAssignableFrom(other.javaClass)

fun String.Companion.htmlFormat(format: String, vararg args: Any?): CharSequence {
    return HtmlCompat.fromHtml(
        if (args.isEmpty()) format else String.format(format, *args),
        HtmlCompat.FROM_HTML_MODE_COMPACT
    )
}

fun String.concatImgUrl(): String = "${BuildConfig.BASE_URL}$this"

fun String.isNameValid(): Boolean = this.matches("^[a-zA-Z]*$".toRegex())

fun String.isPassValid(): Boolean = this.length >= 8

fun String.firstLetterUppercase() = this.split(' ')
    .joinToString(separator = " ") { word -> word.replaceFirstChar { it.uppercase() } }

fun String.firstLetterLowercase() = this.split(' ')
    .joinToString(separator = " ") { word -> word.replaceFirstChar { it.lowercase() } }

fun String.toDate(format: String = Constants.DATE_TIME_FORMAT): Date? =
    SimpleDateFormat(
        format, Locale.getDefault()
    ).parse(this)
