package com.dion.jetshop.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dion.jetshop.data.FurnitureRepository
import com.dion.jetshop.ui.screen.home.HomeViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory (private val repository: FurnitureRepository): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: "+ modelClass.name)
    }
}