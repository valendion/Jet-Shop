package com.dion.jetshop.ui.screen.cart

import com.dion.jetshop.model.OrderFurniture

data class CartState(
    val orderFurniture: List<OrderFurniture>
)
