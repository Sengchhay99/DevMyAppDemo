package com.example.myapplication.helper

import android.content.Context
import android.content.SharedPreferences
import com.example.myapplication.config.Constant

object Session {

    private lateinit var pref: SharedPreferences

    /**
     * init context one time,
     * to use full project without pass context later
     */
    fun initContext(context: Context) {
        pref = context.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE)
    }

    fun putString(key: String, value: String?) {
        pref.edit().putString(key, value).apply()
    }

    fun putBoolean(key: String, value: Boolean) {
        pref.edit().putBoolean(key, value).apply()
    }

    fun putInt(key: String, value: Int) {
        pref.edit().putInt(key, value).apply()
    }

    fun saveVersionName(versionName: String) {
        pref.edit().putString(VERSION, versionName).apply()
    }

    fun isContain(what: String): Boolean {
        return pref.contains(what)
    }

    internal fun isFirstRun(): Boolean {
        val firstRun = getBoolean(FIRST_TIME, true)
        return if (firstRun) {
            putBoolean(FIRST_TIME, false)
            true
        } else {
            false
        }
    }

    internal fun getString(key: String): String {
        return pref.getString(key, "") ?: ""
    }

    internal fun getInt(key: String): Int {
        return pref.getInt(key, 0)
    }

    fun getBoolean(key: String, default: Boolean = false): Boolean {
        return try {
            pref.getBoolean(key, default)
        } catch (ex: Exception) {
            default
        }
    }

    fun clearSharedPreferences() {
        val preferenceEditor = pref.edit()
        preferenceEditor.clear()
        preferenceEditor.apply()
    }

    private const val PACKAGE_NAME = Constant.App.PACKAGE_NAME
    private const val VERSION = Constant.App.VERSION
    private const val FIRST_TIME = Constant.State.FIRST_TIME
}
