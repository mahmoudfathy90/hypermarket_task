package com.example.roubstatask.productList.data.service

import com.example.roubstatask.productList.data.service.response.ProductListModel
import retrofit2.http.GET
import retrofit2.http.Query


interface ListApiService {

    /**
     * function used get the list of products from backend
     * @param productName [String] name of product
     * @param limit [Int]  number of items for list to use by paging
     * @param page [Int]  number of page for  list of returned products results .
     */
    @GET("robustatask/getProducts")
    suspend fun getAllProducts(
        @Query("limit") limit: Int?,
        @Query("page") page: Int?,
        @Query("productName") productName: String?
    ): ProductListModel

}