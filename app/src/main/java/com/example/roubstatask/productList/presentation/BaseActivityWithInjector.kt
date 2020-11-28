package com.example.roubstatask.productList.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.roubstatask.MainActivity
import com.example.roubstatask.productList.presentation.di.module.ViewModelFactory
import com.example.roubstatask.productList.presentation.myApp.MyApplication


import javax.inject.Inject

abstract class BaseActivityWithInjector : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelFactory


    lateinit var vm: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApplication).networkComponent.inject(this)
         vm = ViewModelProviders.of(this, factory)[getFragmentVM()]
    }

    abstract fun getFragmentVM(): Class<out ViewModel>



}