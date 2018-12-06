package com.sopt_nyh.db_design_lecture_project.data

import android.content.Context

object SharedPreferenceController{
    private val USER_NAME = "AUTH"
    private val myAuth = "myAuth"

    fun setAuthorization(context: Context, isInit : Boolean){
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean(myAuth, isInit)
        editor.commit()
    }

    fun getAuthorization(context: Context) : Boolean {
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE)
        return pref.getBoolean(myAuth, false)
    }

    fun clearSPC(context: Context){
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.clear()
        editor.commit()
    }
}