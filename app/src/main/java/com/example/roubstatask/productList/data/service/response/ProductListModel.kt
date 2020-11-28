package com.example.roubstatask.productList.data.service.response


import com.google.gson.annotations.SerializedName

data class ProductListModel(
    @SerializedName("page")
    var productModel: List<ProductModel?>?
) {
    data class ProductModel(
        @SerializedName("id")
        var id: String?,
        @SerializedName("image")
        var image: String?,
        @SerializedName("productName")
        var productName: String?,
        @SerializedName("productPrice")
        var productPrice: String?
    )
}