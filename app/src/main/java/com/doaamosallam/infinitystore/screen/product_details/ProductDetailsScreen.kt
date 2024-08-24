package com.doaamosallam.infinitystore.screen.product_details


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.doaamosallam.domain.models.products.Product
import com.doaamosallam.infinitystore.compose.DetailsItems
import com.doaamosallam.infinitystore.compose.TopBarDetails

@Composable
fun ProductDetailsScreen(
    product: Product,
    onClickBack: () -> Unit,
    onClickFavorite: (Product) -> Unit,
    onClickCart: (Product) -> Unit
) {
    val selectedSize = remember { mutableStateOf("L") }
    val sizes = listOf("S", "M", "L", "XL", "XXL")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        /*
        * TopBar button back, Title name Screen and button Favorite
        * */
        TopBarDetails(product, onClickBack, onClickFavorite)
//        TopBarDetails(onClickBack, onClickFavorite)
        /*
        * Details Items
        * */
        DetailsItems(
            product = product,
            sizes = sizes,
            selectedSize = selectedSize,
            onClickCart = onClickCart

        )

    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewProductDetails() {
    val mockProduct = Product(
        id = 1,
        title = "Sample Product",
        brand = "Sample Brand",
        category = "Sample Category",
        description = "This is a sample product description.",
        price = 19.99,
        discountPercentage = 10.0,
        images = listOf("product.png", "image2.jpg"),
        rating = 4.5,
        reviews = emptyList(),
        thumbnail = "product.png",
        stock = 100
    )

    ProductDetailsScreen(
        product = mockProduct,
        onClickBack = {/* Add back action*/ },
        onClickFavorite = { /*Add to favorite*/ },
        onClickCart = { /* Add to Cart */ },
    )
}