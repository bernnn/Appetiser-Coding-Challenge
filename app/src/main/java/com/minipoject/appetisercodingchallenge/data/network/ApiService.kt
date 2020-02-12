package com.minipoject.appetisercodingchallenge.data.network

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.minipoject.appetisercodingchallenge.BuildConfig
import com.minipoject.appetisercodingchallenge.data.response.Search
import com.minipoject.appetisercodingchallenge.util.AppConfigPreference
import com.minipoject.appetisercodingchallenge.util.getCache
import com.minipoject.appetisercodingchallenge.util.hasNetwork
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.*

interface ApiService {



    companion object {

        private val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        //Not logging the authkey if not debug
        private val client =
            if (BuildConfig.DEBUG) {
                OkHttpClient().newBuilder()
                    .cache(getCache())
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor { chain ->
                        var request = chain.request()
                        request = if(hasNetwork()!!){
                            AppConfigPreference.saveDate(Date().toString())
                            request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                        }else{
                            request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                        }
                        chain.proceed(request)
                    }
                    .build()
            } else {
                OkHttpClient().newBuilder()
                    .build()
            }



        operator fun invoke() : ApiService {
            return Retrofit.Builder()
                .client(client)
                .baseUrl("https://itunes.apple.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(ApiService::class.java)
        }

    }

    @GET("search?term=star&country=au&media=movie")
    suspend fun getList() : Response<Search>

}