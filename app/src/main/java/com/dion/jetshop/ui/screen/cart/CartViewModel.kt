package com.dion.jetshop.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dion.jetshop.data.FurnitureRepository
import com.dion.jetshop.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(private val repository: FurnitureRepository): ViewModel(){

    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<CartState>>
            get() = _uiState

    fun getAddedOrderFunitures(){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderFurnitures()
                .collect{orderFurniture ->
                    _uiState.value = UiState.Success(CartState(orderFurniture, orderFurniture.sumOf { it.count }))
                }
        }
    }

    fun updateOrderFurniture(furnitureId: Long,count: Int){
        viewModelScope.launch {
            repository.updateOrderFurniture(furnitureId,count)
                .collect{isUpdate ->
                    if (isUpdate){
                        getAddedOrderFunitures()
                    }
                }
        }
    }



}