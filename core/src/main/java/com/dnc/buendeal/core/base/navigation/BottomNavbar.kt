package com.dnc.buendeal.core.base.navigation

import com.dnc.buendeal.core.core.ui.view.BottomItem

interface BottomNavbar {
    var currentActiveTab: BottomItem
    var destinationListener: ((activeBottomItem: BottomItem, commitNow: Boolean) -> Unit)?
    fun setActiveTab(activeBottomItem: BottomItem)
    fun setMarker(newNotification: Boolean, bottomItem: BottomItem)
}
