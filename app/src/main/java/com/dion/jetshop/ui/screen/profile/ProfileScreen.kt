package com.dion.jetshop.ui.screen.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dion.jetshop.R
import com.dion.jetshop.ui.theme.JetShopTheme
import com.dion.jetshop.ui.theme.PrimaryColor

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Photo Profile",
            contentScale = ContentScale.Crop,
            modifier =
            Modifier
                .size(150.dp)
                .padding(8.dp)
                .border(
                    border = BorderStroke(2.dp, PrimaryColor),
                    CircleShape
                )
                .clip(CircleShape)
        )

        Text(
            text = stringResource(id = R.string.my_name),
            style = MaterialTheme.typography.subtitle2.copy(
                fontWeight = FontWeight.SemiBold
            )
        )

        Text(
            text = stringResource(id = R.string.my_email),
            style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.Medium
            )
        )

    }
}

