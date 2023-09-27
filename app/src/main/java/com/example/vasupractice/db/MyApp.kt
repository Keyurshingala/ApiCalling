package com.example.vasupractice.db

import android.annotation.SuppressLint
import android.app.Application
import android.content.SharedPreferences
import androidx.room.Room
import com.example.vasupractice.R
import com.example.vasupractice.st

class MyApp : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        @JvmStatic
        lateinit var instance: MyApp

        lateinit var db: AppDatabase
    }

    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    override fun onCreate() {
        super.onCreate()
        instance = this


        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, getString(st.app_name))
                .build()


        preferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE)
        editor = preferences.edit()
    }

    //--------------------common methods-------------------------//
    fun putString(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    fun getString(key: String): String {
        return preferences.getString(key, "") ?: ""
    }

    fun putBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }

    fun getBoolean(key: String): Boolean {
        return preferences.getBoolean(key, false)
    }

    fun putInt(key: String, value: Int) {
        editor.putInt(key, value).apply()
    }

    fun getInt(key: String): Int {
        return preferences.getInt(key, 0)
    }

    fun putFloat(key: String, value: Float) {
        editor.putFloat(key, value).apply()
    }

    fun getFloat(key: String): Float {
        return preferences.getFloat(key, -1f)
    }

    fun putLong(key: String, value: Long) {
        editor.putLong(key, value).apply()
    }

    fun getLong(key: String): Long {
        return preferences.getLong(key, -1L)
    }
    //--------------------common methods-------------------------//


    fun removeAll() {
        editor.clear().commit()
    }
}