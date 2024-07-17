package com.doaamosallam.infinitystore.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.doaamosallam.domain.models.products.Product

@Composable
fun ProductItem(product: Product, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .clickable(onClick = onClick)
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
        SpacerGeneral(Spacer(modifier = Modifier.height(8.dp)))
        Text(
            text = product.price.toString(),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        SpacerGeneral(Spacer(modifier = Modifier.height(8.dp)))
        Text(
            text = product.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Thin,
            color = Color.DarkGray,
            fontFamily = FontFamily.Serif
        )
    }
}

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



