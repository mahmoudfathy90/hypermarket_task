package com.example.roubstatask.productList.presentation.di.component


import com.example.roubstatask.productList.presentation.di.module.ApplicationModule
import com.example.roubstatask.productList.presentation.myApp.MyApplication
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {
    fun injectApp(myApp: MyApplication)

    @Component.Builder
    interface Builder {
        fun builder(): ApplicationComponent
        @BindsInstance
        fun applicationContext(appContext: MyApplication): Builder
    }
}