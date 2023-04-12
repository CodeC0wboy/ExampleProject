package com.dnc.buendeal.core.providers

import com.dnc.buendeal.core.core.domain.model.RecentSearch
import kotlinx.coroutines.flow.Flow

interface PreferencesProvider {

    fun saveString(key: String, value: String?)

    fun getString(key: String): String?

    fun getGuestToken(): String

    fun getComparisonToken(): String

    fun refreshComparisonToken()

    fun saveUserId(userId: Int)

    fun getUserId(): Int

    fun getAuthToken(): String?

    fun saveAuthToken(value: String?)

    fun getAppLanguage(): String?

    fun updateLanguage(value: String)

    fun saveInt(key: String, value: Int)

    fun getInt(key: String): Int

    fun getIntFlow(key: String): Flow<Int>

    fun getBooleanFlow(key: String): Flow<Boolean>

    fun getStringFlow(key: String): Flow<String?>

    fun saveLong(key: String, value: Long)

    fun getLong(key: String): Long

    fun saveBoolean(key: String, value: Boolean)

    fun getBoolean(key: String): Boolean

    fun getBooleanWithDefaultValue(key: String, default: Boolean): Boolean

    fun hasProperty(key: String): Boolean

    fun removeValue(key: String)

    fun saveSearch(query: RecentSearch)

    fun getRecentSearch(): List<RecentSearch>

    fun clearAll()

    fun addToStringSet(key: String, value: String)

    fun getStringSet(key: String): MutableSet<String>

    fun clear(key: String)
    fun getIsOnboardingFinished(): Boolean
    fun saveIsOnboardingFinished(value: Boolean)
}
