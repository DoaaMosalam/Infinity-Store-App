package com.doaamosallam.infinitystore.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.doaamosallam.infinitystore.R

@Composable
fun ImageAuth(painter: Painter, description:String){
    Image(
        painter = painter
        , description,
        modifier = Modifier
            .padding(top = 50.dp)
            .size(120.dp)
    )
}

@Composable
fun Images(painter: Painter, description: String){
    Image(
        painter =painter,
        contentDescription = description,
        modifier = Modifier
            .width(16.dp)
            .height(16.dp)
    )
}