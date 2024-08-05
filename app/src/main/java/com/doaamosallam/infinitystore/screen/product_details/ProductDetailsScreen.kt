package com.doaamosallam.infinitystore.screen.product_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.doaamosallam.infinitystore.compose.TopBarDetails
import com.doaamosallam.infinitystore.ui.theme.Merri

@Composable
fun ProductDetailsContainer(navController: NavController) {
    ProductDetailsScreen(
        onClickBack = { navController.popBackStack() },
        onClickFavorite = { /*TODO*/ }
    )

}

@Composable
fun ProductDetailsScreen(
    onClickBack: () -> Unit,
    onClickFavorite: () -> Unit,

    ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .padding(top = 20.dp, start = 10.dp, end = 10.dp)
                .fillMaxSize()
        ) {
            TopBarDetails(
                modifier = Modifier.padding(top = 20.dp),
                onClickBack = onClickBack,
                onClickFavorite = onClickFavorite,
                text = "Details",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                fontFamily = Merri,
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewProductDetailsContainer() {
    ProductDetailsScreen(
        onClickBack = {},
        onClickFavorite = {},

        )
}