package com.dnc.buendeal.features.dashboard.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.dnc.buendeal.MainActivity.Companion.NAVIGATE_TO_PROFILE
import com.dnc.buendeal.R
import com.dnc.buendeal.base.BaseHostFragment
import com.dnc.buendeal.base.BottomNavigationController
import com.dnc.buendeal.core.core.ui.view.BottomItem
import com.dnc.buendeal.core.extentions.getCurrentFragment
import com.dnc.buendeal.core.extentions.subscribe
import com.dnc.buendeal.databinding.FragmentDashboardBinding

class FragmentDashboard : BaseHostFragment<DashboardViewModel, FragmentDashboardBinding>(
    R.layout.fragment_dashboard,
    FragmentDashboardBinding::bind
) {
 //   private val args by navArgs<FragmentDashboardArgs>()

    private lateinit var bottomNavigationController: BottomNavigationController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bottomNavigationController = BottomNavigationController(
            bottomGraphs = listOf(
                BottomNavigationController.BottomGraph(
                    BottomItem.HOME,
                    R.navigation.navigation_home,
                    R.id.navigation_home
                ),
                BottomNavigationController.BottomGraph(
                    BottomItem.CATALOG,
                    R.navigation.navigation_catalog,
                    R.id.navigation_home
                ),
                BottomNavigationController.BottomGraph(
                    BottomItem.WISHLIST,
                    R.navigation.navigation_wishlist,
                    R.id.navigation_home
                ),
                BottomNavigationController.BottomGraph(
                    BottomItem.CART,
                    R.navigation.navigation_cart,
                    R.id.navigation_home
                ),
                BottomNavigationController.BottomGraph(
                    BottomItem.PROFILE,
                    R.navigation.navigation_profile,
                    R.id.navigation_home
                ),
                BottomNavigationController.BottomGraph(
                    BottomItem.COMPARES,
                    R.navigation.navigation_compares,
                    R.id.navigation_home
                )
            ),
            fragmentManager = childFragmentManager,
            containerId = R.id.dashboardContainer
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBottomNavigation()

        val navigateToProfile = arguments?.getBoolean(NAVIGATE_TO_PROFILE) ?: false
        if (navigateToProfile)
            viewModel.setNewSelectedTab(BottomItem.PROFILE)

//        if (args.showDialog) {
//            SimpleAlertDialog.show(
//                activity?.supportFragmentManager,
//                R.string.new_psw_successfully_set,
//                R.string.you_logged_into_your_account,
//                R.string.ok
//            )
//        }

        // todo remove, it is written only to show marker position
        binding.bottomNavigation.setMarker(true, BottomItem.PROFILE)

        setupSnackBar(navigateToProfile)

        viewModel.setOnboardingFinished()
    }

    private fun setupSnackBar(navigateToProfile: Boolean) {
        if (viewModel.isUserGuest() && !navigateToProfile) {
            showSnackBar()
        }
    }

    private fun showSnackBar() {

//        binding.guestSnackbar.onSignUpClick = {
//            viewModel.navigate(FragmentDashboardDirections.actionFragmentDashboardToSignUpFragment())
//        }

        binding.guestSnackbar.isVisible = true
        binding.guestSnackbar.fadeOutAfter()
    }

    private fun initBottomNavigation() {
        viewModel.connectBottomNavController(
            bottomNavigationController.setup(
                bottomNavigationView = binding.bottomNavigation,
                intent = requireActivity().intent
            )
        )
    }

    override fun observeLiveData() {
        super.observeLiveData()
        subscribe(viewModel.bottomBarVisible) {
            binding.bottomNavigation.isVisible = it
        }
        subscribe(viewModel.selectedTab) {
            binding.bottomNavigation.setActiveTab(it)
        }
    }

    override fun getCurrentFragment(): Fragment? =
        (childFragmentManager.findFragmentById(R.id.dashboardContainer) as? NavHostFragment)?.getCurrentFragment()
}
