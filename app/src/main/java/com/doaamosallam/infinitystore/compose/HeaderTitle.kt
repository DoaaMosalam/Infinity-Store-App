package com.doaamosallam.infinitystore.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.doaamosallam.infinitystore.ui.theme.Merri
import com.doaamosallam.infinitystore.ui.theme.PrimaryColor
import com.doaamosallam.infinitystore.ui.theme.playWriteRegular

@Composable
fun Header( title:String,  subtitle:String){
    Text(
        text = title,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(top = 5.dp),
        fontSize = 20.sp,
        fontFamily = Merri,
        color = PrimaryColor
    )

    Text(
        text = subtitle,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(top = 9.dp),
        fontSize = 9.sp,
        fontFamily = playWriteRegular
    )

}