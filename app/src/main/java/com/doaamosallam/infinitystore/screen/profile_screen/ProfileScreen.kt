package com.doaamosallam.infinitystore.screen.profile_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.doaamosallam.infinitystore.compose.HeaderHome

//state hoisting
@Composable

fun ProfileContainer(navController: NavController) {
    ProfileScreen()
}

@Composable
fun ProfileScreen() {
    ProfileDisplay()

}

@Composable
fun ProfileDisplay() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
    ) {
        HeaderHome(title = "Profile Screen", subtitle = "")
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewProfileScreen() {
    ProfileScreen()
}

