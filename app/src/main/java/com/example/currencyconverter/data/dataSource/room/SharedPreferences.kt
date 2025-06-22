package com.example.currencyconverter.data.dataSource.room

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesManager @Inject constructor(
context: Context
) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun isFirstLaunch(): Boolean {
        return sharedPreferences.getBoolean(KEY_FIRST_LAUNCH, true)
    }

    fun setFirstLaunchDone() {
        editor.putBoolean(KEY_FIRST_LAUNCH, false).apply()
    }

    companion object {
        private const val PREF_NAME = "user_preferences"
        private const val KEY_FIRST_LAUNCH = "is_first_launch"
    }
}