package com.gdsdesenvolvimento.organizecontas.utils.preferences

import android.content.Context

class OrganizePreferences(context: Context) {
    companion object {
        const val NAME_SHARED = "organize_pref"
    }

    private val preferences = context.getSharedPreferences(NAME_SHARED, Context.MODE_PRIVATE)

    fun setStringPref(key : String,value : String){
        preferences.edit().putString(key,value).apply()
    }
    fun getStringPref(key: String): String {
        return preferences.getString(key,"") ?: ""
    }

    fun setIntPref(key : String,value : Int){
        preferences.edit().putInt(key,value).apply()
    }
    fun getIntPref(key: String): Int {
        return preferences.getInt(key,0)
    }

    fun setBooleanPref(key : String,value : Boolean){
        preferences.edit().putBoolean(key,value).apply()
    }
    fun getBooleanPref(key: String): Boolean {
        return preferences.getBoolean(key,false)
    }


}