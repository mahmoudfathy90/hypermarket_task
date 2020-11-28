package com.example.roubstatask.productList.domain



import com.example.roubstatask.productList.data.service.reguest.ListRequestModel
import com.example.roubstatask.productList.data.service.response.ProductListModel
import java.lang.Exception

import javax.inject.Inject

class ListUseCase @Inject constructor(private val repository: IListRepository)
{
    /**
     * function used execute the all Products from repository
     * @param requestModel [ListRequestModel]  model that contains parms
     * @return [ProductListResult] sealed class that indicated success or loading or error or empty
     */
    suspend fun execute(requestModel: ListRequestModel): ProductListResult {
        return try {
            val model = repository.getAllProducts(requestModel)
            if (model.productModel?.isEmpty()!!){
                ProductListResult.Empty
            } else{
                ProductListResult.Success(model.productModel as List<ProductListModel.ProductModel>)
            }
        } catch (ex :Exception){
            ProductListResult.Error(ex)
        }
    }
}