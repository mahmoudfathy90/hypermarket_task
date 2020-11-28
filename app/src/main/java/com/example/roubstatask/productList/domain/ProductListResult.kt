package com.example.roubstatask.productList.domain

import com.example.roubstatask.productList.data.service.response.ProductListModel


sealed class ProductListResult {
    data class Success(val data: List<ProductListModel.ProductModel>) : ProductListResult()
    object Loading : ProductListResult()
    object LoadingMore : ProductListResult()
    data class Error(val throwable: Throwable?) : ProductListResult()
    data class ErrorLoadMore(val throwable: Throwable?) : ProductListResult()
    object Empty : ProductListResult()
}