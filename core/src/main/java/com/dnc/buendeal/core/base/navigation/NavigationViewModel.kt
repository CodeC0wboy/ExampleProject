package com.dnc.buendeal.core.base.navigation

import androidx.lifecycle.MutableLiveData
import com.dnc.buendeal.core.extentions.callAndNullify

interface NavigationViewModel<T : NavigationEvent> {

    val navigationEvent: MutableLiveData<T>
}

fun <T : NavigationEvent> NavigationViewModel<T>.postNavigation(navigation: T) =
    navigationEvent.callAndNullify(navigation)
