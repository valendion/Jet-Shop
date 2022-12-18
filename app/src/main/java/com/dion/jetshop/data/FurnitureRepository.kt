package com.dion.jetshop.data

import com.dion.jetshop.model.FakeFurnitureDataSource
import com.dion.jetshop.model.OrderFurniture
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class FurnitureRepository {
    private val orderFurnitures = mutableListOf<OrderFurniture>()

    init {
        if (orderFurnitures.isEmpty()) {
            FakeFurnitureDataSource.dummyFurnitures.map {
                orderFurnitures.add(OrderFurniture(it, 0))
            }
        }
    }

    fun getAllFurniture(): Flow<List<OrderFurniture>> {
        return flowOf(orderFurnitures)
    }

    fun getOrderFurnitureById(furnitureId: Long): OrderFurniture {
        return orderFurnitures.first {
            it.furniture.id == furnitureId
        }
    }

    fun updateOrderFurniture(furnitureId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderFurnitures.indexOfFirst { it.furniture.id == furnitureId }

        val result = if (index >= 0) {
            val orderFurniture = orderFurnitures[index]
            orderFurnitures[index] =
                orderFurniture.copy(furniture = orderFurniture.furniture, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderFurnitures(): Flow<List<OrderFurniture>> {
        return getAllFurniture().map {
            it.filter { orderFurniture ->
                orderFurniture.count != 0
            }
        }
    }

    companion object {
        @Volatile
        private var instance: FurnitureRepository? = null

        fun getInstance(): FurnitureRepository =
            instance ?: synchronized(this) {
                FurnitureRepository().apply {
                    instance = this
                }
            }
    }
}