package com.dion.jetshop.ui.screen.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dion.jetshop.ui.theme.JetShopTheme
import com.dion.jetshop.ui.theme.PrimaryColor
import com.dion.jetshop.ui.theme.Shapes
import com.dion.jetshop.ui.theme.whiteColor

@Composable
fun CartScreen() {
    CartContent(state = CartState(listOf()), deleteFurnitureFromCart = {})
}

@Composable
fun CartContent(
    state: CartState,
    deleteFurnitureFromCart: (id: Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Icon(
            Icons.Default.ArrowBack,
            tint = whiteColor,
            contentDescription = "Back arrow",
            modifier = Modifier
                .padding(16.dp)
                .clip(Shapes.medium)
                .background(PrimaryColor)
        )
        
        LazyColumn(){
            items(state.orderFurniture){

            }
        }

    }

}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun CartContentPreview() {
    JetShopTheme {
        CartContent(state = CartState(listOf()), deleteFurnitureFromCart = {})
    }
}

