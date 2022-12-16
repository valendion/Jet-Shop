package com.dion.jetshop.di

import com.dion.jetshop.data.FurnitureRepository

object Injection {
    fun provideRepository(): FurnitureRepository{
        return FurnitureRepository.getInstance()
    }
}