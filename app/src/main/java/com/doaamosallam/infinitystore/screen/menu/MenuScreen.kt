package com.doaamosallam.infinitystore.screen.menu

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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuContainer(navController: NavController) {

    MenuScreen(
        onClickBack = { navController.popBackStack() }
    )

}

@Composable
fun MenuScreen(
    onClickBack: () -> Unit,
) {
    MenuDisplay(
        onClickBack = onClickBack
    )

}


@Composable
fun MenuDisplay(
    onClickBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 20.dp, start = 10.dp, end = 10.dp)
            .fillMaxSize()
    ) {
        TopBarScreen(
            modifier = Modifier.padding(top = 20.dp),
            onClickBack = onClickBack,
            text = "Menu",
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            fontFamily = Merri,
            color = Color.DarkGray
        )
        SpacerGeneral(Spacer(modifier = Modifier.height(16.dp)))
        HeaderHome(title = "Menu", subtitle = "")
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewMenuScreen() {
    MenuScreen(
        onClickBack = {}
    )
}