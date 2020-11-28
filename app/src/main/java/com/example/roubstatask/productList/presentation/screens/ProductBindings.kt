package com.example.roubstatask.productList.presentation.screens

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.example.roubstatask.productList.data.service.response.ProductListModel

import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

@BindingMethods(
    value = [
        BindingMethod(type = RecyclerView::class, attribute = "adapter", method = "setAdapter")
    ]
)
object ProductBindings {
    @BindingAdapter("ProductList", "viewState")
    @JvmStatic
    fun RecyclerView.setProductList(
        businessList: PagedList<ProductListModel.ProductModel>?,
        viewState: ProductListState?
    ) {
        businessList?.let { (adapter as? ProductListAdapter)?.submitList(businessList) }
        viewState?.let { (adapter as? ProductListAdapter)?.setState(viewState) }
    }




}