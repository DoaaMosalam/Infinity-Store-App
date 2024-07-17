package com.doaamosallam.infinitystore.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter


@Composable
fun IconButtonHome(
    onClick: () -> Unit,
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier

) {
    Icon(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier.clickable { onClick() })
}

