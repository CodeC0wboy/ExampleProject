package com.dnc.buendeal.core.providers

import android.content.Context
import android.content.SharedPreferences
import com.dnc.buendeal.core.core.domain.model.RecentSearch
import com.dnc.buendeal.core.extentions.offerCatching
import com.dnc.buendeal.core.utils.TextUtils.hash
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.*
import kotlin.collections.HashSet

class PreferencesProviderImpl(context: Context) : PreferencesProvider {

    companion object {
        private const val PREFERENCES = "bdl_preferences"
        private const val UPDATED_FCM_TOKEN = "updated_fcm_token"
        private const val REGISTERED_DEVICE_FCM_TOKEN = "registered_device_fcm_token"
        private const val GUEST_TOKEN = "guest_token"
        private const val USER_ID = "user_id"
        private const val IS_ONBOARDING_FINISHED = "is_onboarding_finished"
        private const val RECENT_SEARCH = "recent_search"

        const val AUTH_TOKEN = "auth_token"
        const val SHOW_DIALOG = "show_dialog"
        const val LANGUAGE = "language"
    }

    private val sharedPreferences =
        context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)

    override fun saveString(key: String, value: String?) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun getString(key: String): String? = sharedPreferences.getString(key, null)

    override fun getGuestToken(): String {
        var token = getString(GUEST_TOKEN)
        if (token == null) {
            token = UUID.randomUUID().toString().replace("-", "")
            saveString(GUEST_TOKEN, token)
        }
        return token
    }

    override fun getComparisonToken(): String = if (getAuthToken() == null) {
        getGuestToken()
    } else {
        getUserId().toString().hash()
    }

    override fun refreshComparisonToken() =
        saveString(GUEST_TOKEN, UUID.randomUUID().toString().replace("-", ""))

    override fun saveUserId(userId: Int) = saveInt(USER_ID, userId)

    override fun getUserId(): Int = getInt(USER_ID)

    override fun getAuthToken() = getString(AUTH_TOKEN)

    override fun saveAuthToken(value: String?) = saveString(AUTH_TOKEN, value)

    override fun getAppLanguage() = getString(LANGUAGE)

    override fun updateLanguage(value: String) = saveString(LANGUAGE, value)

    override fun getIsOnboardingFinished() = getBoolean(IS_ONBOARDING_FINISHED)

    override fun saveIsOnboardingFinished(value: Boolean) =
        saveBoolean(IS_ONBOARDING_FINISHED, value)

    override fun saveInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    override fun getInt(key: String): Int = sharedPreferences.getInt(key, 0)

    override fun getIntFlow(key: String): Flow<Int> = callbackFlow {
        val preferencesListener =
            SharedPreferences.OnSharedPreferenceChangeListener { _, updatedKey ->
                if (updatedKey == key) {
                    offerCatching(getInt(key))
                }
            }

        offerCatching(getInt(key))
        sharedPreferences.registerOnSharedPreferenceChangeListener(preferencesListener)
        awaitClose {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(preferencesListener)
        }
    }

    override fun getBooleanFlow(key: String): Flow<Boolean> = callbackFlow {
        offerCatching(getBoolean(key))
        val preferencesListener =
            SharedPreferences.OnSharedPreferenceChangeListener { _, updatedKey ->
                if (updatedKey == key) {
                    offerCatching(getBoolean(key))
                }
            }

        sharedPreferences.registerOnSharedPreferenceChangeListener(preferencesListener)
        awaitClose {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(preferencesListener)
        }
    }

    override fun getStringFlow(key: String): Flow<String?> = callbackFlow {
        val preferencesListener =
            SharedPreferences.OnSharedPreferenceChangeListener { _, updatedKey ->
                if (updatedKey == key) {
                    offerCatching(getString(key))
                }
            }

        offerCatching(getString(key))
        sharedPreferences.registerOnSharedPreferenceChangeListener(preferencesListener)
        awaitClose {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(preferencesListener)
        }
    }

    override fun saveLong(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    override fun getLong(key: String): Long = sharedPreferences.getLong(key, 0)

    override fun saveBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun getBoolean(key: String): Boolean = sharedPreferences.getBoolean(key, false)

    override fun getBooleanWithDefaultValue(key: String, default: Boolean): Boolean =
        sharedPreferences.getBoolean(key, default)

    override fun hasProperty(key: String): Boolean = sharedPreferences.contains(key)

    override fun removeValue(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    override fun saveSearch(query: RecentSearch) {
        val list = getRecentSearch().toMutableList()
        if (!list.contains(query)) {
            list.add(query)
        }
        val json = Gson().toJson(list)

        sharedPreferences.edit().putString(RECENT_SEARCH, json).apply()
    }

    override fun getRecentSearch(): List<RecentSearch> {
        val json = sharedPreferences.getString(RECENT_SEARCH, null)
        return if (json != null) {
            val type = object : TypeToken<List<RecentSearch?>?>() {}.type
            Gson().fromJson(json, type)
        } else {
            arrayListOf()
        }
    }

    override fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }

    override fun addToStringSet(key: String, value: String) {
        val set = getStringSet(key)
        set.add(value)
        sharedPreferences.edit().putStringSet(key, set).apply()
    }

    override fun getStringSet(key: String): MutableSet<String> =
        sharedPreferences.getStringSet(key, HashSet()) ?: HashSet()

    override fun clear(key: String) {
        removeValue(key)
    }
}
