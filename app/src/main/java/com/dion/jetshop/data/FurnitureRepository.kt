package com.dion.jetshop.data

import com.dion.jetshop.model.FakeFurnitureDataSource
import com.dion.jetshop.model.Furniture
import com.dion.jetshop.model.OrderFurniture
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FurnitureRepository {
    private val orderFurnitures = mutableListOf<OrderFurniture>()

    init {
        if (orderFurnitures.isEmpty()){
            FakeFurnitureDataSource.dummyFurnitures.map {
                orderFurnitures.add(OrderFurniture(it, 0))
            }
        }
    }

    fun getAllFurniture(): Flow<List<OrderFurniture>> {
        return flowOf(orderFurnitures)
    }

    companion object{
        @Volatile
        private var instance: FurnitureRepository? = null

        fun getInstance(): FurnitureRepository =
            instance ?: synchronized(this){
                FurnitureRepository().apply {
                    instance = this
                }
            }
    }
}