package com.doaamosallam.infinitystore.modifier


import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.doaamosallam.infinitystore.ui.theme.Merri
import com.doaamosallam.infinitystore.ui.theme.PrimaryColor


@Composable
fun TextButton(
    text:String,
    onClick:() ->Unit
){
   Text(
       text = text,
       color = PrimaryColor,
       fontSize = 16.sp,
       fontFamily = Merri,
       fontWeight = FontWeight.Normal,
       modifier = Modifier.clickable { onClick() }
       )
}