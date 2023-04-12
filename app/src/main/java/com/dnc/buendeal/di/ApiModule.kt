package com.dnc.buendeal.di

import com.dnc.buendeal.core.BuildConfig
import com.dnc.buendeal.core.network.*
import com.dnc.buendeal.core.network.retrofit.RequestAuthInterceptor
import com.dnc.buendeal.core.utils.ExceptionParser
import com.dnc.buendeal.network.error.ErrorCallAdapterFactory
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val API_AUTHORIZED_CLIENT_QUALIFIER = named("API_AUTHORIZED_CLIENT")
private val API_UNAUTHORIZED_CLIENT_QUALIFIER = named("API_UNAUTHORIZED_CLIENT")
private val API_UNAUTHORIZED_VERIFIED_CLIENT_QUALIFIER = named("API_UNAUTHORIZED_VERIFIED_CLIENT")

val MOCKED_SOURCE = named("mocked_source")
val NETWORK_SOURCE = named("network_source")

const val GSON = "gson"

val apiModule = module {
    factory { GsonConverterFactory.create(get<Gson>(named(GSON))) }
    single { ExceptionParser(get()) }

    single {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    single(API_UNAUTHORIZED_CLIENT_QUALIFIER) {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(RequestAuthInterceptor(get()))
            .build()

        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(get<GsonConverterFactory>())
            .addCallAdapterFactory(ErrorCallAdapterFactory(get(named(GSON))))
            .client(okHttpClient)
            .build()
    }

    single(API_UNAUTHORIZED_VERIFIED_CLIENT_QUALIFIER) {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(RequestAuthInterceptor(get()))
            .build()

        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(get<GsonConverterFactory>())
            .addCallAdapterFactory(ErrorCallAdapterFactory(get(named(GSON))))
            .client(okHttpClient)
            .build()
    }

    single(API_AUTHORIZED_CLIENT_QUALIFIER) {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(RequestAuthInterceptor(get()))
            .build()
    }

    single(API_AUTHORIZED_CLIENT_QUALIFIER) {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(get<GsonConverterFactory>())
            .addCallAdapterFactory(ErrorCallAdapterFactory(get(named(GSON))))
            .client(get(API_AUTHORIZED_CLIENT_QUALIFIER))
            .build()
    }

    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    fun provideApiWishlist(retrofit: Retrofit): ApiWishlist =
        retrofit.create(ApiWishlist::class.java)

    fun provideApiAuth(retrofit: Retrofit): ApiAuth =
        retrofit.create(ApiAuth::class.java)

    fun provideApiStore(retrofit: Retrofit): ApiStore =
        retrofit.create(ApiStore::class.java)

    fun provideApiComparisonList(retrofit: Retrofit): ApiComparisonLists =
        retrofit.create(ApiComparisonLists::class.java)

    fun provideApiSearch(retrofit: Retrofit): ApiSearch =
        retrofit.create(ApiSearch::class.java)

    factory { provideApiWishlist(get(API_UNAUTHORIZED_CLIENT_QUALIFIER)) }

    factory { provideApiService(get(API_UNAUTHORIZED_CLIENT_QUALIFIER)) }

    factory { provideApiAuth(get(API_UNAUTHORIZED_CLIENT_QUALIFIER)) }

    factory { provideApiStore(get(API_UNAUTHORIZED_CLIENT_QUALIFIER)) }

    factory { provideApiComparisonList(get(API_UNAUTHORIZED_CLIENT_QUALIFIER)) }

    factory { provideApiSearch(get(API_UNAUTHORIZED_CLIENT_QUALIFIER)) }
}
