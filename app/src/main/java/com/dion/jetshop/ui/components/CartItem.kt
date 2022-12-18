package com.dion.jetshop.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
    furnitureId: Long,
    image: Int,
    title: String,
    price: Long,
    count: Int,
    deleteItem: (Long, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .clip(Shapes.medium)
            .background(whiteColor)
            .fillMaxWidth()
            .height(70.dp),
        horizontalArrangement = Arrangement.SpaceBetween,

        ) {

        Image(
            painter = painterResource(id = image), contentDescription = null, modifier = Modifier
                .size(70.dp)
                .clip(RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp))
        )

        Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
            Text(
                text = title,
                maxLines = 1,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.ExtraBold
                ),

                textAlign = TextAlign.Center,
                color = PrimaryTextColor,
                modifier = Modifier.align(Alignment.CenterHorizontally)

            )

            Text(
                text = stringResource(R.string.required_price, price),
                color = SecondTextColor,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2.copy(
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
                .clickable {
                    deleteItem(furnitureId, count - 1)
                }
        )
    }
}

