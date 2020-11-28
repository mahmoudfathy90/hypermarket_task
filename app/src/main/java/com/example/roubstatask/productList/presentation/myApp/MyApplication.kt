package com.example.roubstatask.productList.presentation.myApp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.roubstatask.productList.presentation.util.Constants
import com.example.roubstatask.productList.presentation.di.component.ApplicationComponent
import com.example.roubstatask.productList.presentation.di.component.DaggerApplicationComponent
import com.example.roubstatask.productList.presentation.di.component.DaggerNetworkComponent
import com.example.roubstatask.productList.presentation.di.component.NetworkComponent


class MyApplication : Application() {

    private lateinit var appComponent: ApplicationComponent
    lateinit var networkComponent: NetworkComponent


    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.builder()
            .applicationContext(this)
            .builder()

        networkComponent = DaggerNetworkComponent.builder()
            .application(this)
            .baseUrl(Constants.BASE_URL)
            .builder()


    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
