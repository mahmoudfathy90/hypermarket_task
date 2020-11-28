package com.example.roubstatask.productList.presentation.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.roubstatask.productList.Constants.Companion.PAGE_SIZE
import com.example.roubstatask.productList.data.service.response.ProductListModel
import javax.inject.Inject

class ProductListViewModel @Inject constructor(private val item: ItemDataSourceFactory) :
    ViewModel() {
    var itemPagedList: LiveData<PagedList<ProductListModel.ProductModel>>
   //    val searchText = MutableLiveData<String>()
   //    val isSearching = MutableLiveData<Boolean>(false)
   // val hideSoftKeyboard = EnhancedLiveEvent<Unit>()


    init {
        val config = PagedList.Config.Builder().setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        itemPagedList = LivePagedListBuilder(item, config)
            .build()
    }

    fun stateEvent() = item.stateEvent()

    fun search() {
//        hideSoftKeyboard.call()
//        val term = searchText.value
//        item.search(term)
//        isSearching.postValue(true)
    }



    fun clearSearch() {
//        isSearching.postValue(false)
//        searchText.postValue(null)
//        item.search(null)
    }

    override fun onCleared() {
        item.getDataSource().cancelJob()
        super.onCleared()
    }

    fun retry() {
        item.getDataSource().retry?.let { it() }
    }
}