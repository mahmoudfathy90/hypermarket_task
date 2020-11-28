package com.example.roubstatask.productList.presentation.screens

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.roubstatask.productList.presentation.util.Constants.Companion.FIRSTPAGE
import com.example.roubstatask.productList.presentation.util.Constants.Companion.PAGE_SIZE
import com.example.roubstatask.productList.data.service.reguest.ListRequestModel
import com.example.roubstatask.productList.data.service.response.ProductListModel
import com.example.roubstatask.productList.domain.ListUseCase
import com.example.roubstatask.productList.domain.ProductListResult
import kotlinx.coroutines.*
import javax.inject.Inject


class ItemDataSource @Inject constructor() :
    PageKeyedDataSource<Int, ProductListModel.ProductModel>() {
    @Inject
     lateinit var useCase: ListUseCase

    private var defaultViewState = ProductListState()
    val stateEvent = MutableLiveData<ProductListState>()
    private var modelRequest = ListRequestModel(name = "", limit = PAGE_SIZE, page = FIRSTPAGE)
    var retry: (() -> Unit)? = null
    private lateinit var job: Job
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ProductListModel.ProductModel>
    ) {
        loadData(onLoading = {
            stateEvent.postValue(defaultViewState.copy(loading = true))
        }, onError = {
            stateEvent.postValue(defaultViewState.copy(error = it))
        },
            onEmpty = {
                stateEvent.postValue(defaultViewState.copy(empty = true))
            }
        ) { result ->
            callback.onResult(result.data, null, FIRSTPAGE)
        }
    }

    fun search(name: String?) {
        modelRequest = modelRequest.copy(name = name)
    }

    private fun loadData(
        param: LoadParams<Int>? = null,
        onLoading: () -> Unit,
        onError: (Throwable?) -> Unit,
        onEmpty: () -> Unit,
        onResult: (ProductListResult.Success) -> Unit
    ) {
        val currentPage = param?.key ?: FIRSTPAGE
        Log.e("currentPAge ", " : $currentPage")
        job = GlobalScope.launch(Dispatchers.IO) {
            onLoading()
            val result = useCase.execute(
                requestModel = modelRequest.copy(
                    page = currentPage
                )
            )
            when (result) {
                is ProductListResult.Error -> {
                    onError(result.throwable)
                    retry = { loadData(param, onLoading, onError, onEmpty, onResult) }
                }
                is ProductListResult.Empty -> {
                    onEmpty()
                }
                is ProductListResult.Success -> {
                    onResult.invoke(result)
                }
            }

        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ProductListModel.ProductModel>
    ) {
        loadData(
            params,
            onLoading = {
                stateEvent.postValue(defaultViewState.copy(loadingMore = true))
            },
            onError = {
                stateEvent.postValue(defaultViewState.copy(errorLoadMore = it))
            }, onEmpty = {
                stateEvent.postValue(defaultViewState.copy(empty = true))
            }
        )
        {
            callback.onResult(it.data, params.key + 1)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ProductListModel.ProductModel>
    ) {
        //Ignored because loadInitial
    }

    /**
     * function to cancel the GlobalScope in view Model
     */

    fun cancelJob() {
        job.cancel()
    }


}

data class ProductListState(
    val loading: Boolean = false,
    val empty: Boolean = false,
    val error: Throwable? = null,
    val loadingMore: Boolean = false,
    val errorLoadMore: Throwable? = null
)
