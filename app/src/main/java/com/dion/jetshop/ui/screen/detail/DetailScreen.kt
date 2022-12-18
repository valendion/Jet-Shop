package com.dion.jetshop.ui.screen.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dion.jetshop.R
import com.dion.jetshop.di.Injection
import com.dion.jetshop.ui.ViewModelFactory
import com.dion.jetshop.ui.common.UiState
import com.dion.jetshop.ui.theme.*

@Composable
fun DetailScreen(
    furnitureId: Long,
    viewModel: DetailFurnitureViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateBack: () -> Unit,
    navigateToCart: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getFurnitureById(furnitureId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    image = data.furniture.image,
                    title = data.furniture.title,
                    price = data.furniture.price,
                    count = data.count,
                    description = data.furniture.description,
                    onBackClick = navigateBack,
                    onAddToCart = { count ->
                        viewModel.addToCart(data.furniture, count = count)
                        navigateToCart()
                    })
            }
            is UiState.Error -> {}
        }

    }
}

@Composable
fun DetailContent(
    image: Int,
    title: String,
    price: Long,
    count: Int,
    description: String,
    onBackClick: () -> Unit,
    onAddToCart: (count: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var orderCount by rememberSaveable { mutableStateOf(count) }

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
                .background(BackgroundWhiteColor)
        ) {
            Box {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                )

                Icon(
                    Icons.Default.ArrowBack,
                    tint = whiteColor,
                    contentDescription = "Back arrow",
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(Shapes.medium)
                        .background(PrimaryColor)
                        .clickable { onBackClick() }
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold),
                    color = PrimaryTextColor
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Justify,
                    color = SecondTextColor,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(16.dp)
                .wrapContentSize(),

            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = stringResource(R.string.required_price, price),
                style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Medium),
                color = PrimaryTextColor
            )

            Button(
                enabled = orderCount == 0,
                onClick = {
                    orderCount++
                    onAddToCart(orderCount)
                },
                content = {
                    Text(text = stringResource(id = R.string.add_to_cart))
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
                    .clip(Shapes.medium),
            )
        }
    }

}

