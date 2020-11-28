package com.example.roubstatask.productList.data.repository

import com.example.roubstatask.productList.data.service.ListApiService
import com.example.roubstatask.productList.data.service.reguest.ListRequestModel
import com.example.roubstatask.productList.data.service.response.ProductListModel
import com.example.roubstatask.productList.domain.IListRepository
import javax.inject.Inject


class ListRepository @Inject constructor(private val apiService: ListApiService) : IListRepository
{
    override suspend fun getAllProducts(requestModel: ListRequestModel): ProductListModel {
        return apiService.getAllProducts(
            limit = requestModel.limit, page = requestModel.page,productName = requestModel.name
        )
    }

}