package com.example.roubstatask.productList.presentation.screens

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.roubstatask.productList.data.service.response.ProductListModel


import javax.inject.Inject
import javax.inject.Provider


class ItemDataSourceFactory @Inject constructor(private var item: ItemDataSource) :
    DataSource.Factory<Int, ProductListModel.ProductModel>() {
    @Inject lateinit var itemDataSourceProvider : Provider<ItemDataSource>
    private val itemLiveDataSource: MutableLiveData<PageKeyedDataSource<Int, ProductListModel.ProductModel>> =
        MutableLiveData()


    override fun create(): DataSource<Int, ProductListModel.ProductModel> {
        itemLiveDataSource.postValue(item)
        return item
    }

    fun stateEvent() = item.stateEvent
    fun getDataSource() = item
    fun search(term: String?) {
        item = itemDataSourceProvider.get()
        item.search(term)
        itemLiveDataSource.value?.invalidate()
    }

}