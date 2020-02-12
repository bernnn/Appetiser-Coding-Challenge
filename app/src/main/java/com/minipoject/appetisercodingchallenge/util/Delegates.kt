package com.minipoject.appetisercodingchallenge.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.minipoject.appetisercodingchallenge.AppetiserApplication
import kotlinx.coroutines.*
import okhttp3.Cache
import java.text.SimpleDateFormat
import java.util.*

fun<T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>>{
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}

fun hasNetwork(): Boolean? {
    var isConnected: Boolean? = false // Initial Value
    val connectivityManager = AppetiserApplication.appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}

fun getCache(): Cache{
    val cacheSize = (5.times(1024).times(1024)).toLong()
    return Cache(AppetiserApplication.appContext.cacheDir, cacheSize)
}

fun getSavedDate(): String{
    val current = Date()

    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    return sdf.format(current)
}