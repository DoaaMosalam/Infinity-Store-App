package com.doaamosallam.infinitystore.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.doaamosallam.domain.models.products.Product
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

@Composable
fun TopBarDetails(
    product: Product,
    onClickBack: () -> Unit,
    onClickFavorite: (Product) -> Unit
) {
    var isBookmarked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ClickableIconBack(
            painterResource(id = R.drawable.baseline_arrow_back_ios_24),
            contentDescription = null,
            onClick = onClickBack,

            )
        Text(
            text = stringResource(R.string.details),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Normal
        )
        IconButton(onClick = {
            isBookmarked = !isBookmarked
            onClickFavorite(product)
        }) {
            Icon(
                painter = painterResource(
                    id = if (isBookmarked) R.drawable.baseline_bookmark_24
                    else R.drawable.outline_bookmark_border_24
                ),
                contentDescription = stringResource(R.string.bookmark),
                tint = if (isBookmarked) Color.Red else Color.Black
            )
        }
    }
    SpacerGeneral(modifier = Modifier.height(30.dp))
}

@Composable
fun TopBarFavorite(
    onClickBack: () -> Unit,
    onclickCart: () -> Unit
) {
    var isSelect by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClickBack
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = null
            )
        }

        Text(
            text = stringResource(R.string.favorite),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Normal
        )
        IconButton(onClick = {
            isSelect = !isSelect
            onclickCart()
        }) {
            Icon(
                painter = painterResource(

                    id = if (isSelect) R.drawable.shopping_bag_24
                    else R.drawable.outline_shopping_bag_24
                ),
                contentDescription = stringResource(R.string.bookmark),
                tint = if (isSelect) Color.Red else Color.Black
            )
        }
    }

}

