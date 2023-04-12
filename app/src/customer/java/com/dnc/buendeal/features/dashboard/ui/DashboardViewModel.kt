package com.dnc.buendeal.features.dashboard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import com.dnc.buendeal.R
import com.dnc.buendeal.base.BaseViewModel
import com.dnc.buendeal.core.core.ui.view.BottomItem
import com.dnc.buendeal.core.extentions.mutable
import com.dnc.buendeal.core.providers.PreferencesProvider
import com.dnc.buendeal.core.utils.SingleLiveEvent

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine

class DashboardViewModel(private val preferencesProvider: PreferencesProvider) : BaseViewModel() {

    lateinit var currentBottomNavController: LiveData<NavController>

    lateinit var isStartDestination: LiveData<Boolean>

    val bottomBarVisible: LiveData<Boolean> = MutableLiveData(true)

    val selectedTab = SingleLiveEvent<BottomItem>()

    init {
        launch {
            currentDestination.collect {
                bottomBarVisible.mutable().value =
                    when (it?.id) {
                        else -> true
                    }
            }
        }
    }

    fun connectBottomNavController(navControllerFlow: StateFlow<NavController>) {
        currentBottomNavController = navControllerFlow.asLiveData()

        isStartDestination =
            combine(currentDestination, navControllerFlow) { navDestination, navController ->
                navController.graph.startDestinationId == navDestination?.id
            }.asLiveData()
    }

    fun navigateProfileToSingIn() {

    }

    fun navigateComparesToCatalog() {
        setNewSelectedTab(BottomItem.CATALOG)
    }

    fun isUserGuest() = preferencesProvider.getAuthToken().isNullOrEmpty()

    fun setNewSelectedTab(item: BottomItem) {
        selectedTab.value = item
    }

    fun setOnboardingFinished() {
        preferencesProvider.saveIsOnboardingFinished(true)
    }
}
