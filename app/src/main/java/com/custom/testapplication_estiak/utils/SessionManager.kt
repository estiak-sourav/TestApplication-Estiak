package com.custom.testapplication_estiak.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.custom.testapplication_estiak.models.Repositorie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SessionManager @SuppressLint("CommitPrefEdits") constructor(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(Pref_Name, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun setSavedRepositories(repositorie: Repositorie?) {
        val gson = Gson()
        val json = gson.toJson(repositorie)
        editor.putString(savedRepositories, json)
        editor.apply()
        editor.commit()
    }

    val repositories: Repositorie
        get() {
            val gson = Gson()
            val json = sharedPreferences.getString(savedRepositories, null)
            val type = object : TypeToken<Repositorie?>() {}.type
            return gson.fromJson(json, type)
        }

    fun clearRepositories() {
        editor.remove(savedRepositories)
        editor.apply()
        editor.commit()
    }

    companion object {
        private const val Pref_Name = "TestApplication"
        private const val savedRepositories = "repositories"
    }

}