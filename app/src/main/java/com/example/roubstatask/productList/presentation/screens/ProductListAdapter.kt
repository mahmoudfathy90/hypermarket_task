package com.example.roubstatask.productList.presentation.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IntDef
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roubstatask.R
import com.example.roubstatask.databinding.ErrorLayout
import com.example.roubstatask.databinding.ProductItemLayout
import com.example.roubstatask.productList.data.service.response.ProductListModel



class ProductListAdapter(var pInterface: ProductInterface) :
    PagedListAdapter<ProductListModel.ProductModel, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    private var viewState = ProductListState()
        set(value) {
            if (field == value) return
            if (value.loadingMore || value.errorLoadMore != null) {
                if (field.loadingMore || field.errorLoadMore != null) {
                    notifyItemChanged(currentList?.size!!)
                } else {
                    notifyItemInserted(currentList?.size!!)
                }
            } else {
                notifyItemRemoved(currentList?.size!!)
            }
            field = value
        }

    override fun getItemCount(): Int {
        if (viewState.loadingMore || viewState.errorLoadMore != null) {
            return super.getItemCount() + 1
        }
        return super.getItemCount()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == currentList?.size) {
            when {
                viewState.loadingMore -> {
                    AdapterViewType.LOADING
                }
                viewState.errorLoadMore != null -> {
                    AdapterViewType.ERROR
                }
                else -> {
                    AdapterViewType.NORMAL
                }
            }
        } else AdapterViewType.NORMAL
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductListModel.ProductModel>() {
            // The ID property identifies when items are the same.
            override fun areItemsTheSame(oldItem: ProductListModel.ProductModel, newItem: ProductListModel.ProductModel) =
                oldItem.id == newItem.id

            // If you use the "==" operator, make sure that the object implements
            // .equals(). Alternatively, write custom data comparison logic here.
            override fun areContentsTheSame(
                oldItem: ProductListModel.ProductModel, newItem: ProductListModel.ProductModel
            ) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            AdapterViewType.LOADING -> {
                val view = layoutInflater.inflate(R.layout.loading, parent, false)
                object : RecyclerView.ViewHolder(view) {}
            }
            AdapterViewType.ERROR -> {
                val binding = ErrorLayout.inflate(layoutInflater, parent, false)
                ErrorViewHolder(binding, viewState.errorLoadMore, pInterface)
            }
            else -> {
                val binding = ProductItemLayout.inflate(layoutInflater, parent, false)
                ItemViewHolder(binding)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            if (itemCount - 1 > position) {
                val item: ProductListModel.ProductModel = getItem(position)!!
                holder.bind(item)
            }
        }

    }

    fun setState(viewState: ProductListState) {
        this.viewState = viewState
    }

    class ItemViewHolder(
        private val binding: ProductItemLayout
    ) :
        RecyclerView.ViewHolder(binding.root) {



        fun bind(item: ProductListModel.ProductModel) {
            binding.model = item
        }
    }

    class ErrorViewHolder(
        binding: ErrorLayout,
        val error: Throwable?,
        productInterface: ProductInterface
    ) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.error = error
            binding.handler = productInterface
        }
    }
}

@IntDef(AdapterViewType.LOADING, AdapterViewType.ERROR, AdapterViewType.NORMAL)
annotation class AdapterViewType {
    companion object {
        const val LOADING = 1
        const val ERROR = 2
        const val NORMAL = 3
    }
}
