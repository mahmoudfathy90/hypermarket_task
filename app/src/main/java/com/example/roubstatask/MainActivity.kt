package com.example.roubstatask

import android.os.Bundle
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.roubstatask.databinding.MainBinding
import com.example.roubstatask.productList.presentation.BaseActivityWithInjector
import com.example.roubstatask.productList.presentation.screens.ProductListViewModel
import com.example.roubstatask.productList.presentation.util.hideKeyboard
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : BaseActivityWithInjector() {

    private val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController
    }

    lateinit var binding: MainBinding
    private val productListViewModel: ProductListViewModel by lazy {
        ViewModelProviders.of(this).get(ProductListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(toolBar)
//        binding.apply {
//            viewModel = productListViewModel
//            lifecycleOwner = this@MainActivity
//        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
           productListViewModel.search(query)
                return false
            }
        })


        setupActionBarWithNavController(navController)
    }



    override fun getFragmentVM(): Class<out ViewModel> {
        return ProductListViewModel::class.java
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

}