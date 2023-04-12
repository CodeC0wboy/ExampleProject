package com.dnc.buendeal.core.extentions

import android.os.Bundle

fun Bundle.put(key: String, data: Any?) {
    data ?: return
    when (data) {
        is String -> putString(key, data)
        is Int -> putInt(key, data)
        is Long -> putLong(key, data)
        is List<*> -> putStringArrayList(key, ArrayList(data.map { it.toString() }))
        else -> putString(key, data.toString())
    }
}
