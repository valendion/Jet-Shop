package com.dion.jetshop.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dion.jetshop.data.FurnitureRepository
import com.dion.jetshop.model.OrderFurniture
import com.dion.jetshop.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: FurnitureRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderFurniture>>> =
        MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<List<OrderFurniture>>>
        get() = _uiState

    fun getAllFurniture(){
        viewModelScope.launch {
            repository.getAllFurniture()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }

                .collect{orderFurniture ->
                    _uiState.value = UiState.Success(orderFurniture)
                }
        }
    }

}