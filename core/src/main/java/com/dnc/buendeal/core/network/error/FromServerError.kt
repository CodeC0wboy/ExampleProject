package com.dnc.buendeal.core.network.error

import java.io.IOException

open class FromServerError(
    override val message: String,
    val code: Int,
    cause: Throwable?,
    val errorsMap: Map<String, Any>? = null,
    val headers: Map<String, String>? = null
) : IOException(message, cause)
