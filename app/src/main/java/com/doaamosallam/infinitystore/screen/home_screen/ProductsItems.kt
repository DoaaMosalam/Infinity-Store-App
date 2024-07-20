package com.doaamosallam.infinitystore.screen.home_screen

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.doaamosallam.domain.models.categories.CategoriesItem
import com.doaamosallam.domain.models.products.Product
import com.doaamosallam.infinitystore.R
import com.doaamosallam.infinitystore.compose.IconButtonCart
import com.doaamosallam.infinitystore.compose.SpacerGeneral

@Composable
fun ProductItem(product: Product,
                onClickProduct: () -> Unit,
                onClickCart: () -> Unit,
                modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .clickable(onClick = onClickProduct)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxHeight()
                .clickable { onClickProduct },
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

        }
        SpacerGeneral(Spacer(modifier = Modifier.height(8.dp)))
        Text(
            text = "${"$"} ${product.price}",
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

        IconButtonCart(
            onClick = onClickCart,
            painter = painterResource(id = R.drawable.shopping_bag_24),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.
            size(40.dp)
                .background(Color.Black, shape = CircleShape)
                .padding(4.dp) )
    }
}

// Category List in Home Screen
@Composable
fun CategoryItem(collection: String, isSelected: Boolean, onClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick(collection) }
    ) {
        Text(
            text = collection,
            modifier = Modifier
                .padding(5.dp)
                .background(
                    color = if (isSelected) Color(0xFFFF7A00) else Color.White, // Change to orange if selected
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(8.dp),
            fontSize = 16.sp, // Adjust your font size
            fontWeight = FontWeight.Normal,
            color = if (isSelected) Color.White else Color.Black // Change text color based on selection
        )
    }
}

// Categories in Category Screen
@Composable
fun CategoriesItem(category: CategoriesItem, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
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
                painter =
                rememberImagePainter(data = category.url),
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
                fontSize = 15.sp,
                fontWeight = FontWeight.Thin,
                color = Color.DarkGray,
                fontFamily = FontFamily.Serif
            )
        }
    }
}

@Composable
fun CartItem(cart: CategoriesItem, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
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
                painter =
                rememberImagePainter(data = cart.url),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .height(300.dp)
                    .width(300.dp)
                    .background(Color.White)
            )
            SpacerGeneral(Spacer(modifier = Modifier.height(8.dp)))
            Text(
                text = cart.name,
                fontSize = 15.sp,
                fontWeight = FontWeight.Thin,
                color = Color.DarkGray,
                fontFamily = FontFamily.Serif
            )
        }
    }
}




