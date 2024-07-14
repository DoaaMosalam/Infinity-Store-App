package com.doaamosallam.infinitystore.screen.home_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.doaamosallam.infinitystore.compose.Header


//state hoisting
@Composable
fun HomeScreen(

) {

}

// home screen
@Composable
fun HomeContainer() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )

    {
        Header(title = "Home Screen", subtitle = "Products")


    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeContainer()

}
