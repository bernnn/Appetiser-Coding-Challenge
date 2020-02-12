package com.minipoject.appetisercodingchallenge.util

import android.content.Context
import com.minipoject.appetisercodingchallenge.AppetiserApplication

private const val PREF_DATE = "pref_date"


class AppConfigPreference{


    companion object{

        internal val sharedPreference by lazy {
            AppetiserApplication.appContext.getSharedPreferences("app_config", Context.MODE_PRIVATE)
        }

        fun saveDate(date: String?) { save(PREF_DATE,date) }

        private fun save(key: String, value: String?){
            sharedPreference.edit().putString(
                key, value).apply()
        }


    }








}