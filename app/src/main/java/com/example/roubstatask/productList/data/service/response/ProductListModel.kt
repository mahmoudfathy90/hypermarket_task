package com.example.roubstatask.productList.data.service.response


import com.google.gson.annotations.SerializedName

data class ProductListModel(
    @SerializedName("page")
    var page: List<Page?>?
) {
    data class Page(
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