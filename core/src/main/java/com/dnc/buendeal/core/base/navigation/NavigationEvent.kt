package com.dnc.buendeal.core.base.navigation

import com.dnc.buendeal.core.utils.SingleLiveEvent

interface NavigationEvent

object Finish : NavigationEvent

fun SingleLiveEvent<Finish>.postFinish() = postValue(Finish)
