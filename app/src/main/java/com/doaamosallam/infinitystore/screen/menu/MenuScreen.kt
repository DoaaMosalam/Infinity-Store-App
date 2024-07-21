package com.doaamosallam.infinitystore.screen.menu

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.doaamosallam.infinitystore.R
import com.doaamosallam.infinitystore.compose.ClickableImage
import com.doaamosallam.infinitystore.compose.HeaderHome
import com.doaamosallam.infinitystore.compose.SpacerGeneral
import com.doaamosallam.infinitystore.navigation.BottomNavigationBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuContainer(navController: NavController) {

        MenuScreen()

}

@Composable
fun MenuScreen(
    onClickBack: () -> Unit = {}
) {
    MenuDisplay()

}

@Composable
fun MenuDisplay(
    onClickBack: () -> Unit = {}
) {
    Column(modifier = Modifier
        .padding(top = 30.dp, start = 10.dp, end = 10.dp)
        .fillMaxSize()
    ) {
        ClickableImage(
            painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
            contentDescription = null,
            onClick = onClickBack
        )
        SpacerGeneral(Spacer(modifier = Modifier.height(16.dp)))
        HeaderHome(title = "Menu", subtitle = "")
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewPCategoryScreen() {
    MenuScreen()
}