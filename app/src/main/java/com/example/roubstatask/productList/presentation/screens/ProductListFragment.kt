package com.example.roubstatask.productList.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.roubstatask.BaseFragmentWithInjector
import com.example.roubstatask.databinding.ProductList



class ProductListFragment : BaseFragmentWithInjector(), ProductInterface {


    private val productAdapter by lazy { ProductListAdapter( this) }


    private val productListViewModel: ProductListViewModel by lazy {
        ViewModelProviders.of(this).get(ProductListViewModel::class.java)
    }

    private lateinit var binding: ProductList


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProductList.inflate(inflater, container, false)
        binding.apply {
            viewModel = productListViewModel
            lifecycleOwner = viewLifecycleOwner
            adapter = productAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun getFragmentVM(): Class<out ViewModel> {
        return ProductListViewModel::class.java
    }



    override fun retry() {
        productListViewModel.retry()
    }

}




