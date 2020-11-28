package com.example.roubstatask.productList.presentation.myApp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex



class MyApplication : Application() {
//
//    private lateinit var appComponent: ApplicationComponent
//    lateinit var networkComponent: NetworkComponent


    override fun onCreate() {
        super.onCreate()

//        appComponent = DaggerApplicationComponent.builder()
//            .applicationContext(this)
//            .builder()
//
//        networkComponent = DaggerNetworkComponent.builder()
//            .application(this)
//            .baseUrl(Constants.BASE_URL)
//            .builder()


    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
