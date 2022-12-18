package com.dion.jetshop.ui.screen.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dion.jetshop.R
import com.dion.jetshop.di.Injection
import com.dion.jetshop.ui.ViewModelFactory
import com.dion.jetshop.ui.common.UiState
import com.dion.jetshop.ui.components.CartItem
import com.dion.jetshop.ui.theme.BackgroundWhiteColor
import com.dion.jetshop.ui.theme.JetShopTheme
import com.dion.jetshop.ui.theme.PrimaryTextColor

@Composable
fun CartScreen(
    viewModel: CartViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    )
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAddedOrderFunitures()
            }

            is UiState.Success -> {
                if (uiState.data.orderFurniture.isNotEmpty()){
                    CartContent(state = uiState.data, deleteFurnitureFromCart = { furnitureId, count ->
                        viewModel.updateOrderFurniture(furnitureId, count)
                    })
                }else{
                    EmptyContent()
                }

            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun EmptyContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
            .background(BackgroundWhiteColor),
        verticalArrangement = Arrangement.Center
//        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.no_data),
            style = MaterialTheme.typography.h5.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            textAlign = TextAlign.Center,
            color = PrimaryTextColor,
            modifier = Modifier.align(CenterHorizontally)

        )
    }
}

@Composable
fun CartContent(
    state: CartState,
    deleteFurnitureFromCart: (Long, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize().background(BackgroundWhiteColor)) {

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.orderFurniture, key = { it.furniture.id }) { item ->
                CartItem(
                    furnitureId = item.furniture.id,
                    image = item.furniture.image,
                    title = item.furniture.title,
                    count = item.count,
                    price = item.furniture.price,
                    deleteItem = deleteFurnitureFromCart
                )
            }
        }

    }

}


