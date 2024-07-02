package com.doaamosallam.infinitystore.modifier

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

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