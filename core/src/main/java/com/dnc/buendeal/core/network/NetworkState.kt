package com.dnc.buendeal.network

sealed class NetworkState {
    object Default : NetworkState()
    object Loading : NetworkState()
    object InitialLoading : NetworkState()
    object Loaded : NetworkState()
    data class Error(val t: Throwable, val retry: suspend () -> Any) : NetworkState()
    data class InitialError(val t: Throwable, val retry: suspend () -> Any) : NetworkState()
}
