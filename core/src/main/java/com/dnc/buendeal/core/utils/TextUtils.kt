package com.dnc.buendeal.core.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.Toast
import java.security.MessageDigest

object TextUtils {

    val creditCardTextWatcher: TextWatcher = object : TextWatcher {

        private var current = ""

        private val nonDigits = Regex("[^\\d]")

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun afterTextChanged(s: Editable) {
            if (s.toString() != current) {
                val userInput = s.toString().replace(nonDigits, "")
                if (userInput.length <= 16) {
                    current = userInput.chunked(4).joinToString(" ")
                    s.filters = arrayOfNulls<InputFilter>(0)
                }
                s.replace(0, s.length, current, 0, current.length)
            }
        }
    }

    val creditCardDateTextWatcher: TextWatcher = object : TextWatcher {
        private var current = ""

        private val nonDigits = Regex("[^\\d]")

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun afterTextChanged(s: Editable) {
            if (s.toString() != current) {
                val userInput = s.toString().replace(nonDigits, "")
                if (userInput.length <= 4) {
                    current = userInput.chunked(2).joinToString("/")
                    s.filters = arrayOfNulls<InputFilter>(0)
                }
                s.replace(0, s.length, current, 0, current.length)
            }
        }
    }

    val securityCodeTextWatcher: TextWatcher = object : TextWatcher {
        private var current = ""

        private val nonDigits = Regex("[^\\d]")

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun afterTextChanged(s: Editable) {
            if (s.toString() != current) {
                val userInput = s.toString().replace(nonDigits, "")
                if (userInput.length <= 3) {
                    current = userInput.chunked(2).joinToString("")
                    s.filters = arrayOfNulls<InputFilter>(0)
                }
                s.replace(0, s.length, current, 0, current.length)
            }
        }
    }

    fun String.copyTextToClipboard(context: Context) {
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text", this)
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(context, "Text copied to clipboard", Toast.LENGTH_LONG).show()
    }

    fun String.hash(): String {
        val bytes = this.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }
}
