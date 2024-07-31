package com.doaamosallam.infinitystore.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.doaamosallam.infinitystore.ui.theme.Merri
import com.doaamosallam.infinitystore.ui.theme.PrimaryColor
import com.doaamosallam.infinitystore.ui.theme.playWriteRegular

/* text = title,
      textAlign = TextAlign.Center,
      modifier = Modifier.padding(top = 5.dp),
      fontSize = 20.sp,
     fontWeight =  FontWeight.Bold)*/
@Composable
fun TextGeneral(
    title: String,
    modifier: Modifier,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    fontFamily: FontFamily,
    color: Color,
) {
    Text(
        text = title,
        textAlign = TextAlign.Center,
        modifier = modifier,
        fontSize = fontSize,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        color = color
    )

}


@Composable
fun Header(
    title: String,
    subtitle: String
) {
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

@Composable
fun HeaderHome(title: String, subtitle: String) {
    Text(
        text = title,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(top = 20.dp, start = 16.dp),
        fontSize = 30.sp,
        fontFamily = Merri,
        color = Color.Black
    )

    Text(
        text = subtitle,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(top = 5.dp, start = 16.dp),
        fontSize = 16.sp,
        fontFamily = playWriteRegular,
        color = Color.Gray
    )
}