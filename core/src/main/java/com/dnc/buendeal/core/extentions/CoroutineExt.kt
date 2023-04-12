package com.dnc.buendeal.core.extentions

import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.io.IOException

/**
 * Workaround b/c of exceptions that [SendChannel.offer] throws in case of closed channel..
 * @return false if any exception catched on offer function
 */
fun <E> SendChannel<E>.offerCatching(element: E): Boolean {
    return runCatching { trySend(element).isSuccess }.getOrDefault(false)
}

/**
 * https://github.com/Kotlin/kotlinx.coroutines/issues/1446
 */
fun <T> Flow<T>.throttleFirst(windowDuration: Long): Flow<T> = flow {
    var lastEmissionTime = 0L
    collect { upstream ->
        val currentTime = System.currentTimeMillis()
        val mayEmit = currentTime - lastEmissionTime > windowDuration
        if (mayEmit) {
            lastEmissionTime = currentTime
            emit(upstream)
        }
    }
}

/**
 * original: https://stackoverflow.com/a/46890009
 */
suspend fun <T> retryIO(
    times: Int = Int.MAX_VALUE,
    initialDelay: Long = 1000, // 0.1 second
    maxDelay: Long = 1000, // 1 second
    factor: Double = 2.0,
    block: suspend () -> T
): T {
    var currentDelay = initialDelay
    repeat(times - 1) {
        try {
            return block()
        } catch (e: IOException) {
            // you can log an error here and/or make a more finer-grained
            // analysis of the cause to see if retry is needed
        }
        delay(currentDelay)
        currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelay)
    }
    return block() // last attempt
}
