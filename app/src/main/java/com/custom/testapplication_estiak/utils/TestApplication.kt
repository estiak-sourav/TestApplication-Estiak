package com.custom.testapplication_estiak.utils

import android.app.Application
import android.net.ConnectivityManager

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (instance == null) {
            instance = this
        }
    }// connected to the mobile provider's data plan
    // connected to wifi

    // connected to the internet
    private val isNetworkConnected: Boolean
        get() {
            val connMgr = instance!!.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connMgr.activeNetworkInfo
            if (activeNetworkInfo != null) { // connected to the internet
                if (activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI) {
                    // connected to wifi
                    return true
                } else if (activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE) {
                    // connected to the mobile provider's data plan
                    return true
                }
            }
            return false
        }

    companion object {
        private var instance: TestApplication? = null
        fun hasNetwork(): Boolean {
            return instance!!.isNetworkConnected
        }
    }
}