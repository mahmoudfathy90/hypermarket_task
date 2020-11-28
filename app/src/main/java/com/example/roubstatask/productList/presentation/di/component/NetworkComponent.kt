package com.example.roubstatask.productList.presentation.di.component



import androidx.fragment.app.Fragment
import com.example.roubstatask.BaseFragmentWithInjector
import com.example.roubstatask.productList.presentation.BaseActivityWithInjector
import com.example.roubstatask.productList.presentation.di.module.ApplicationModule
import com.example.roubstatask.productList.presentation.di.module.ListModule
import com.example.roubstatask.productList.presentation.di.module.NetworkModule
import com.example.roubstatask.productList.presentation.di.module.ViewModelModule
import com.example.roubstatask.productList.presentation.myApp.MyApplication
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApplicationModule::class, NetworkModule::class, ViewModelModule::class
        , ListModule::class]
)
interface NetworkComponent {


  fun inject(fragment: BaseFragmentWithInjector)
  fun inject(activity: BaseActivityWithInjector)

    @Component.Builder
    interface NetworkBuilder {
        fun builder(): NetworkComponent
        @BindsInstance
        fun application(app: MyApplication): NetworkBuilder
        @BindsInstance
        fun baseUrl(baseUrl: String): NetworkBuilder

    }
}