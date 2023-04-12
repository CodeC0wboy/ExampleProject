package com.dnc.buendeal.core.network.retrofit

import com.dnc.buendeal.core.providers.PreferencesProvider
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class RequestAuthInterceptor(private val preferencesHelper: PreferencesProvider) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val builder = chain.request().newBuilder()

        preferencesHelper.getAuthToken()?.let {
            builder.addHeader(
                "Authorization".trim(),
                "Bearer ${it.trim()}"
            )
        }

        val response = chain.proceed(builder.build())

        if (!response.isSuccessful) {
            if (response.code == 401 || response.code == 403) {
                preferencesHelper.saveAuthToken(null)
                preferencesHelper.refreshComparisonToken()
            }
        }

        return response
    }
}
