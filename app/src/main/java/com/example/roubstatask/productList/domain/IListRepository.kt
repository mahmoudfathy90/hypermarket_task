package com.example.roubstatask.productList.domain

import com.example.roubstatask.productList.data.service.reguest.ListRequestModel
import com.example.roubstatask.productList.data.service.response.ProductListModel

interface IListRepository {
     /**
      * function used to get  the list of Product from api service
      * @param requestModel [ListRequestModel]  model  that contains all param to use it  @Query
      * in ListApiService
      * @return ProductListModel[ProductListModel] model that have all data for products
      */
     suspend fun getAllProducts(requestModel: ListRequestModel): ProductListModel

}