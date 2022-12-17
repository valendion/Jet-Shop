package com.dion.jetshop.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dion.jetshop.R
import com.dion.jetshop.di.Injection
import com.dion.jetshop.model.OrderFurniture
import com.dion.jetshop.ui.ViewModelFactory
import com.dion.jetshop.ui.common.UiState
import com.dion.jetshop.ui.components.FurnitureItem
import com.dion.jetshop.ui.theme.BackgroundWhiteColor
import com.dion.jetshop.ui.theme.JetShopTheme
import com.dion.jetshop.ui.theme.PrimaryTextColor

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository()))
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {

            is UiState.Loading -> {
                viewModel.getAllFurniture()
            }

            is UiState.Success -> {
                HomeContent(orderFurniture = uiState.data, navigateToDetail = navigateToDetail)
            }

            is UiState.Error -> {}

        }
    }
}

@Composable
fun HomeContent(
    orderFurniture: List<OrderFurniture>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit
) {
    Column(modifier = Modifier.background(BackgroundWhiteColor)) {
        Text(
            text = stringResource(id = R.string.find_better),
            fontWeight = FontWeight.Medium,
            color = PrimaryTextColor,
            fontSize = 24.sp,
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 2.dp, end = 16.dp)
        )

        Text(
            text = stringResource(id = R.string.furniture_for_your_living),
            fontWeight = FontWeight.Medium,
            color = PrimaryTextColor,
            fontSize = 24.sp,
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            modifier = Modifier
                .padding(start = 16.dp, top = 2.dp, bottom = 16.dp, end = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(140.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
        ) {
            items(orderFurniture) {
                FurnitureItem(
                    image = it.furniture.image,
                    title = it.furniture.title,
                    price = it.furniture.price,
                    modifier = Modifier
                        .clickable {
                            navigateToDetail(it.furniture.id)
                        })
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    JetShopTheme {
        HomeContent(listOf(), navigateToDetail = {})
    }
}