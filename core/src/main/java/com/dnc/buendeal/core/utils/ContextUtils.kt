package com.dnc.buendeal.core.utils

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import java.util.*

class ContextUtils(base: Context) : ContextWrapper(base) {

    companion object {
        fun updateLocale(c: Context, localeToSwitchTo: String): ContextWrapper {
            var context = c
            val resources: Resources = context.resources
            val configuration: Configuration = resources.configuration
            val locale = Locale(localeToSwitchTo)
            Locale.setDefault(locale)
            configuration.setLocale(locale)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                context = context.createConfigurationContext(configuration)
            } else {
                resources.updateConfiguration(configuration, resources.displayMetrics)
            }
            return ContextUtils(context)
        }
    }
}
