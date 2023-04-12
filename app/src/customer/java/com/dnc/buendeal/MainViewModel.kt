package com.dnc.buendeal

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dnc.buendeal.base.BaseViewModel
import com.dnc.buendeal.core.providers.PreferencesProvider
import com.dnc.buendeal.core.utils.BaseResult
import com.dnc.buendeal.core.utils.SingleLiveEvent
import com.dnc.buendeal.core.utils.wrapResult
import com.dnc.buendeal.core.network.ConnectionStatusListener
import kotlinx.coroutines.runBlocking

class MainViewModel(
    private val connectionStatusListener: ConnectionStatusListener,
    private val preferencesProvider: PreferencesProvider
) : BaseViewModel() {

    val startDestinationInitialized = SingleLiveEvent<Pair<Int, Bundle?>>()

    val isKeyboardVisible = MutableLiveData<Boolean>()

    val connectionListenerFlow = connectionStatusListener.connectionStatusChannel

    fun initStartDestination() {
        launchThrowable {
            startDestinationInitialized.value = R.id.fragmentDashboard to null
        }
    }

    fun initStartDestinationBy(destinationId: Int, bundle: Bundle? = null) {
        startDestinationInitialized.value = destinationId to bundle
    }

    // update with api
    fun isAuthorized(): Boolean = runBlocking(viewModelScope.coroutineContext) {
        when (val result = wrapResult { true }) {
            is BaseResult.Success -> {
                result.data
            }
            is BaseResult.Error -> {
                false
            }
        }
    }
}
