package com.example.roubstatask.productList.presentation.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.roubstatask.productList.presentation.util.Constants.Companion.PAGE_SIZE
import com.example.roubstatask.productList.data.service.response.ProductListModel
import com.example.roubstatask.productList.presentation.util.EnhancedLiveEvent
import kotlinx.coroutines.*
import javax.inject.Inject

class ProductListViewModel @Inject constructor(private val factory: ItemDataSourceFactory) :
    ViewModel() {
    var itemPagedList: LiveData<PagedList<ProductListModel.ProductModel>>
    private lateinit var job: Job




    init {
        val config = PagedList.Config.Builder().setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        itemPagedList = LivePagedListBuilder(factory, config)
            .build()
    }

    fun stateEvent() = factory.stateEvent()

    fun search(name: String) {

        factory.search(name)
//      job=GlobalScope.launch(){
//           delay(2000)
//
//        }
    }


    fun clearSearch() {
//        isSearching.postValue(false)
//        searchText.postValue(null)
//        item.search(null)
    }

    override fun onCleared() {
        factory.getDataSource().cancelJob()
        job.cancel()
        super.onCleared()
    }

    fun retry() {
        factory.getDataSource().retry?.let { it() }
    }
}