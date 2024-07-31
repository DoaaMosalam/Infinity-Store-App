package com.doaamosallam.infinitystore.compose

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.doaamosallam.infinitystore.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBarScreen(
    onClickBack: () -> Unit,
    modifier: Modifier,
    text: String,
    fontWeight: FontWeight,
    fontSize: TextUnit,
    fontFamily: FontFamily,
    color: Color,
) {
    TopAppBar(
        modifier = Modifier,
        title = {
            TextGeneral(
                title = text,
                modifier = Modifier,
                fontSize = fontSize,
                fontWeight = fontWeight,
                fontFamily = fontFamily,
                color = color
            )
        },
        navigationIcon = {
            ClickableIconBack(
                painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = null,
                onClick = onClickBack,
            )
        },
    )
}
