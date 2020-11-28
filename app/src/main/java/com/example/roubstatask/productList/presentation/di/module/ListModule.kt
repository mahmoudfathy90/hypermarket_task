package com.example.roubstatask.productList.presentation.di.module




import androidx.lifecycle.ViewModel
import com.example.roubstatask.productList.data.repository.ListRepository
import com.example.roubstatask.productList.domain.IListRepository
import com.example.roubstatask.productList.presentation.screens.ProductListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
 abstract  class ListModule {


 @Binds
 abstract fun businessList(repository: ListRepository) : IListRepository


 @Binds
 @IntoMap
 @ViewModelKey(ProductListViewModel::class)
 internal abstract fun itemViewModel(viewModel: ProductListViewModel): ViewModel



}