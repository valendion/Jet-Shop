package com.dion.jetshop.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dion.jetshop.data.FurnitureRepository
import com.dion.jetshop.model.Furniture
import com.dion.jetshop.model.OrderFurniture
import com.dion.jetshop.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailFurnitureViewModel(
    private val repository: FurnitureRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderFurniture>> = MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<OrderFurniture>>
    get() = _uiState

    fun getFurnitureById(furnitureId: Long){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderFurnitureById(furnitureId))
        }
    }

    fun addToCart(furniture: Furniture, count: Int){
        viewModelScope.launch {
            repository.updateOrderFurniture(furniture.id, count)
        }
    }
}