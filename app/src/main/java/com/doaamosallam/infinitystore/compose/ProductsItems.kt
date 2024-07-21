package com.doaamosallam.infinitystore.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.doaamosallam.domain.models.cart.CartProduct
import com.doaamosallam.domain.models.categories.CategoriesItem
import com.doaamosallam.domain.models.products.Product
import com.doaamosallam.infinitystore.R


@Composable
fun ProductItem(
    product: Product,
    onClickProduct: () -> Unit,
    onClickCart: (Product) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp)
            .clickable(onClick = onClickProduct),
        colors = CardDefaults.cardColors(
            colorResource(id = R.color.melon_moledy)
        )

    ) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(7.dp)
            .clickable(onClick = onClickProduct)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onClickProduct() },
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                painter =
                rememberImagePainter(data = product.thumbnail),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .height(200.dp)
                    .background(Color.White)
            )
            IconButtonCart(
                onClick = { onClickCart(product) },
                painter = painterResource(id = R.drawable.shopping_bag_24),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Black, shape = CircleShape)
                    .padding(4.dp)
            )

        }
        SpacerGeneral(Spacer(modifier = Modifier.height(8.dp)))
        Text(
            text = "\$${product.price}",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        SpacerGeneral(Spacer(modifier = Modifier.height(8.dp)))
        Text(
            text = product.title,
            fontSize = 15.sp,
            fontWeight = FontWeight.Thin,
            color = Color.DarkGray,
            fontFamily = FontFamily.Serif
        )
        Spacer(modifier = Modifier.weight(1f))
    }

    }
}

// Category List in Home Screen

@Composable
fun CategoryItem(collection: String, isSelected: Boolean, onClick: (String) -> Unit) {
    val backgroundColor = if (isSelected) Color(0xFFFF7A00) else Color.White
    val textColor = if (isSelected) Color.White else Color.Black

    Text(
        text = collection,
        color = textColor,
        modifier = Modifier
            .padding(5.dp)
            .clickable { onClick(collection) }
            .background(
                color = if (isSelected) Color(0xFFFF7A00) else Color.White, // Change to orange if selected
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),

        style = MaterialTheme.typography.bodyLarge.copy(background = backgroundColor)
    )
}

// Categories in Category Screen
@Composable
fun CategoriesItem(category: CategoriesItem, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp),
        colors = CardDefaults.cardColors(
            colorResource(id = R.color.melon_moledy)
        )

    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(7.dp)
                .clickable(onClick = onClick),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxHeight(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
//                rememberImagePainter(data = category.url),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .height(300.dp)
                        .width(300.dp)
                        .background(Color.White)
                )
                SpacerGeneral(Spacer(modifier = Modifier.height(8.dp)))
                Text(
                    text = category.name,
                    modifier = Modifier.padding(15.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.baby_blue),
                    fontFamily = FontFamily.Default
                )
            }
        }
    }
}

@Composable
fun CartItem(
    cart: CartProduct,
    onClickDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .scrollable(
                orientation = Orientation.Horizontal,
                enabled = true,
                state = rememberScrollState())
            .fillMaxSize()
            .height(200.dp)
            .padding(7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(data = cart.thumbnail),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .background(Color.White)
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
                Text(
                    text = cart.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = cart.discountPercentage.toString(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$${cart.price}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        Text(
            text = "${cart.quantity}x",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )
        Spacer(modifier = Modifier.width(25.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .size(50.dp)
                .weight(1f)
                .background(
                    colorResource(id = R.color.primary_color),
                    shape = CircleShape.copy(
                        topEnd = CornerSize(10.dp),
                        bottomEnd = CornerSize(10.dp),
                    )
//                    RoundedCornerShape(corner = CornerSize(10.dp))
                )
                .clickable { onClickDelete() },
        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.baseline_favorite_border_24),
//                contentDescription = "Wishlist",
//                tint = colorResource(id = R.color.white),
//                modifier = Modifier.size(24.dp)
//            )
//            Spacer(modifier = Modifier.height(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.outline_delete),
                contentDescription = "Delete",
                tint = colorResource(id = R.color.white),
                modifier = Modifier.size(28.dp)
            )

        }
    }

}



