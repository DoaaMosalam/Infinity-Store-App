package com.doaamosallam.infinitystore.screen.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.doaamosallam.infinitystore.compose.HeaderHome
import com.doaamosallam.infinitystore.compose.SpacerGeneral
import com.doaamosallam.infinitystore.compose.TopBarScreen
import com.doaamosallam.infinitystore.ui.theme.Merri

//state hoisting
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun ProfileContainer(navController: NavController) {
    ProfileScreen(
        onClickBack = { navController.popBackStack() }
    )
}

@Composable
fun ProfileScreen(
    onClickBack: () -> Unit,
) {
    ProfileDisplay(
        onClickBack = onClickBack
    )

}

@Composable
fun ProfileDisplay(
    onClickBack: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(top = 20.dp, start = 10.dp, end = 10.dp)
            .fillMaxSize()
    ) {
        TopBarScreen(
            modifier = Modifier.padding(top = 20.dp),
            onClickBack = onClickBack,
            text = "Profile",
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            fontFamily = Merri,
            color = Color.DarkGray
        )
        SpacerGeneral(Spacer(modifier = Modifier.height(16.dp)))
        HeaderHome(title = "Profile", subtitle = "")
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewProfileScreen() {
    ProfileScreen(
        onClickBack = {}
    )
}

