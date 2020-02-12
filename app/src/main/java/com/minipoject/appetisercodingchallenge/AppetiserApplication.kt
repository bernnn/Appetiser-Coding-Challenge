package com.minipoject.appetisercodingchallenge

import android.app.Application
import android.content.Context
import com.minipoject.appetisercodingchallenge.data.network.ApiService
import com.minipoject.appetisercodingchallenge.module.main.MainRepo
import com.minipoject.appetisercodingchallenge.util.MainViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class AppetiserApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@AppetiserApplication))

        bind() from  singleton { ApiService() }
        bind() from  singleton {
            MainRepo(
                instance()
            )
        }
        bind() from  provider { MainViewModelFactory(instance()) }
    }

    companion object{
        lateinit  var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }


}