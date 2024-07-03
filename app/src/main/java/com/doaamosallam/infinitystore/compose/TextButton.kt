package com.doaamosallam.infinitystore.compose


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.doaamosallam.infinitystore.R
import com.doaamosallam.infinitystore.ui.theme.Gray_Dark
import com.doaamosallam.infinitystore.ui.theme.Merri
import com.doaamosallam.infinitystore.ui.theme.PrimaryColor


@Composable
fun Text(text:String){
    Text(text = text)
}
@Composable
fun ForgetTextButton(
    text:String,
    onClick:() ->Unit,
    modifier: Modifier,
){
   Text(
       text = text,
       color = PrimaryColor,
       fontSize = 13.sp,
       fontFamily = Merri,
       fontWeight = FontWeight.Normal,
       modifier = Modifier.clickable { onClick() },

       )
}
@Composable
fun RegisterTextButton(
    text:String,
    onClick:() ->Unit,
    modifier: Modifier,
){
    Text(
        text = text,
        color = PrimaryColor,
        fontSize = 13.sp,
        fontFamily = Merri,
        fontWeight = FontWeight.Normal,
        modifier = Modifier.clickable { onClick() },

        )
}