package com.example.roubstatask.productList.presentation.di.module

import android.content.Context
import com.example.roubstatask.productList.presentation.di.qualifiers.ForApplication
import com.example.roubstatask.productList.presentation.myApp.MyApplication

import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    @ForApplication
    fun applicationContext(app: MyApplication): Context {
        return app.applicationContext
    }

}