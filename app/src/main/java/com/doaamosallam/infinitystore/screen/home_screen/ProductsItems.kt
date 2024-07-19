package com.doaamosallam.infinitystore.screen.home_screen

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.doaamosallam.domain.models.products.Product
import com.doaamosallam.infinitystore.R
import com.doaamosallam.infinitystore.compose.SpacerGeneral

@Composable
fun ProductItem(product: Product, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxHeight()
                .clickable { onClick() },
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
            Image(
                painter =
                painterResource(id = R.drawable.shopping_bag_24),
                contentDescription = null,
                Modifier
                    .size(40.dp)
                    .background(Color.Black, shape = CircleShape)
                    .clickable { onClick() }
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
fun CategoriesItem(category: Product, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxHeight()
                .clickable { onClick() },
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                painter =
                rememberImagePainter(data = category.thumbnail),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .height(200.dp)
                    .background(Color.White)
            )
            SpacerGeneral(Spacer(modifier = Modifier.height(8.dp)))
            Text(
                text = category.title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Thin,
                color = Color.DarkGray,
                fontFamily = FontFamily.Serif
            )
        }
    }
}




