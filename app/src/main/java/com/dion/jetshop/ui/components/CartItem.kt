package com.dion.jetshop.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dion.jetshop.R
import com.dion.jetshop.ui.theme.*

@Composable
fun CartItem(
    image: Int,
    title: String,
    price: Long,
    deleteItem: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .clip(Shapes.medium)
            .background(whiteColor)
            .fillMaxWidth()
            .height(40.dp),



        horizontalArrangement = Arrangement.SpaceBetween,

    ) {

        Image(
            painter = painterResource(id = image), contentDescription = null, modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp))
        )

        Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
            Text(
                text = title,
                maxLines = 1,
                style = MaterialTheme.typography.caption.copy(
                    fontWeight = FontWeight.ExtraBold
                ),

                textAlign = TextAlign.Center,
                color = PrimaryTextColor,
                modifier = Modifier.align(Alignment.CenterHorizontally)

            )

            Text(
                text = stringResource(R.string.required_price, price),
                color = SecondTextColor,
                style = MaterialTheme.typography.overline.copy(
                    fontWeight = FontWeight.Medium
                ),
            )


        }

        Icon(
            Icons.Default.Close,
            tint = whiteColor,
            contentDescription = null,
            modifier = Modifier
                .padding(end = 8.dp)
                .clip(Shapes.medium)
                .background(PrimaryColor)
                .align(Alignment.CenterVertically)


        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF4F4F4)
@Composable
fun CartItemPreview() {
    JetShopTheme {
        CartItem(image = R.drawable.shelf1, title = "Rak", price = 2000, deleteItem = { })
    }
}