package com.gonexwind.ourstory.utils

import android.content.Context
import android.content.SharedPreferences
import com.gonexwind.ourstory.utils.Constants.PREFS_IS_LOGIN
import com.gonexwind.ourstory.utils.Constants.PREFS_NAME
import com.gonexwind.ourstory.utils.Constants.PREFS_TOKEN

class UserPrefs(context: Context) {
    private var prefs: SharedPreferences =
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val editPrefs = prefs.edit()

    val getToken = prefs.getString(PREFS_TOKEN, "")
    val isLogin = prefs.getBoolean(PREFS_IS_LOGIN, false)

    fun setStringPrefs(key: String, value: String) {
        editPrefs.putString(key, value)
        editPrefs.apply()
    }

    fun setBooleanPrefs(key: String, value: Boolean) {
        editPrefs.putBoolean(key, value)
        editPrefs.apply()
    }
}