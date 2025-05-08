package com.example.travelsmanagement

import android.content.Context

object UserData {
    private const val PREF_NAME = "user_prefs"
    private const val KEY_NAME = "name"
    private const val KEY_EMAIL = "email"
    private const val KEY_PASSWORD = "password"
    private const val KEY_IMAGE_PATH = "image_path"

    fun saveUser(context: Context, name: String, email: String, password: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().apply {
            putString(KEY_NAME, name)
            putString(KEY_EMAIL, email)
            putString(KEY_PASSWORD, password)
            apply()
        }
    }

    fun saveImagePath(context: Context, path: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_IMAGE_PATH, path).apply()
    }

    fun getImagePath(context: Context): String? {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_IMAGE_PATH, null)
    }

    fun getEmail(context: Context): String? =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString(KEY_EMAIL, null)

    fun getPassword(context: Context): String? =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString(KEY_PASSWORD, null)

    fun getName(context: Context): String? =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString(KEY_NAME, null)

    fun clearUser(context: Context) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit().clear().apply()
    }
}
