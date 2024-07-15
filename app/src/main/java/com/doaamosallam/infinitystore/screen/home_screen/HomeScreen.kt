package com.doaamosallam.infinitystore.screen.home_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.doaamosallam.infinitystore.compose.Header
import com.doaamosallam.infinitystore.compose.Text
import com.doaamosallam.infinitystore.util.Screen
import com.doaamosallam.infinitystore.viewmodel.home.HomeIntent
import com.doaamosallam.infinitystore.viewmodel.home.HomeViewModel
import com.doaamosallam.infinitystore.viewmodel.home.HomeViewState


//state hoisting

@Composable
fun HomeContainer(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val viewState by homeViewModel.viewState.collectAsState()

}

@Composable
private fun HomeScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally){

    }

    Header(title = "Home Screen", subtitle = "Products")
}
@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen(){
    HomeScreen()
}
