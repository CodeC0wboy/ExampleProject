package com.dnc.buendeal

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.dnc.buendeal.core.base.navigation.NavigationCommandHandler
import com.dnc.buendeal.core.extentions.isKeyboardInset
import com.dnc.buendeal.core.extentions.showToast
import com.dnc.buendeal.core.extentions.subscribe
import com.dnc.buendeal.core.providers.PreferencesProviderImpl
import com.dnc.buendeal.core.utils.ContextUtils
import com.dnc.buendeal.databinding.ActivityMainBinding
import com.dnc.buendeal.features.splash.SplashFragment
import com.dnc.buendeal.core.network.ConnectionStatusListener
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModel()

    private val navigationCommandHandler: NavigationCommandHandler by lazy {
        NavigationCommandHandler(
            navControllerDefinition = { binding.navHost.findNavController() }
        )
    }

    override fun attachBaseContext(newBase: Context) {
        val preferences = PreferencesProviderImpl(newBase)
        val localeToSwitchTo = preferences.getAppLanguage()
        if (localeToSwitchTo == null) preferences.updateLanguage("es")
        val localeUpdatedContext: ContextWrapper =
            ContextUtils.updateLocale(newBase, localeToSwitchTo ?: "es")
        super.attachBaseContext(localeUpdatedContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        initKeyboardListener()
        initFragment()
        subscribeConnectionListener()
    }

    fun refreshActivity() {
        finish()
        startActivity(intent.putExtra(NAVIGATE_TO_PROFILE, true))
        overridePendingTransition(0, 0)
    }

    private fun initViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun initKeyboardListener() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.navHost) { _, windowInsets ->
            mainViewModel.isKeyboardVisible.value = windowInsets.isKeyboardInset(this)
            windowInsets
        }
    }

    private fun initFragment() {
        val fragment = supportFragmentManager.findFragmentById(R.id.navHost)
        if (fragment != null) {
            onNavGraphInited()
            return
        } else {
            val notShowOnboarding = intent.getBooleanExtra(NAVIGATE_TO_PROFILE, false)

            if (notShowOnboarding) {
                val bundle = Bundle().apply { putBoolean(NAVIGATE_TO_PROFILE, true) }
                mainViewModel.initStartDestinationBy(R.id.fragmentDashboard, bundle)
            } else {
                mainViewModel.initStartDestination()
            }
            subscribe(mainViewModel.startDestinationInitialized) { destinationWithBundle ->
                initNavigationGraph(destinationWithBundle.first, destinationWithBundle.second)
            }
        }
    }

    private fun onNavGraphInited() {
        lifecycle.coroutineScope.launch {
            val fragment = supportFragmentManager.findFragmentById(R.id.navHost)

            fragment?.lifecycle?.whenStarted {
                fragment.findNavController().addOnDestinationChangedListener { _, destination, _ ->
                    mainViewModel.currentDestination.value = destination
                }

                fragment.view?.post {
                    startSplashAnimation()
                }
            }
        }
        subscribe(mainViewModel.navigationCommand) {
            navigationCommandHandler.handle(this, it)
        }
    }

    private fun subscribeConnectionListener() {
        subscribe(mainViewModel.connectionListenerFlow) {
            updateNoConnectionMessage(it)
        }
    }

    private fun initNavigationGraph(startDestinationId: Int, startDestinationBundle: Bundle?) {
        val navHostFragment = NavHostFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.navHost, navHostFragment)
            .setPrimaryNavigationFragment(navHostFragment)
            .commitNow()

        val graph =
            navHostFragment.navController.navInflater.inflate(R.navigation.navigation_main)
                .apply {
                    setStartDestination(startDestinationId)
                }
        navHostFragment.navController.setGraph(graph, startDestinationBundle)
        onNavGraphInited()
    }

    private fun startSplashAnimation() {
        val splashFragment =
            supportFragmentManager.findFragmentById(R.id.splashHost) as? SplashFragment
        splashFragment?.startSplashAnimation {
            lifecycleScope.launchWhenResumed {
                if (splashFragment.isAdded) {
                    supportFragmentManager.beginTransaction().remove(splashFragment).commitNow()
                    binding.frameContainer.removeView(binding.splashHost)
                }
            }
        }
    }

    private fun updateNoConnectionMessage(status: ConnectionStatusListener.ConnectionStatusState) {
        when (status) {
            ConnectionStatusListener.ConnectionStatusState.Default,
            ConnectionStatusListener.ConnectionStatusState.HasConnection,
            ConnectionStatusListener.ConnectionStatusState.Dismissed -> {
                showToast("Network connected")
            }

            ConnectionStatusListener.ConnectionStatusState.NoConnection -> {
                showToast("Network disconnected")
            }
        }
    }

    companion object {
        const val NAVIGATE_TO_PROFILE = "navigate_to_profile"
    }
}
