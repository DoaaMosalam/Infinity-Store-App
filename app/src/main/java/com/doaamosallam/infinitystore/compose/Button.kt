package com.doaamosallam.infinitystore.compose

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GenericButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonText: String,
    buttonColor: Color,
    textColor: Color
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .width(250.dp)
            .height(40.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = textColor
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 10.dp
        )
    ) {
        Text(
            text = buttonText,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
fun CheckOut_PayNow(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonText: String,
    buttonColor: Color,
    fontWeight: FontWeight,
    fontSize: Int,
    color: Color,
    fontFamily: FontFamily,
    textColor: Color
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .width(250.dp)
            .height(40.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = textColor
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 10.dp
        )
    ) {
        Text(
            text = buttonText,
            fontWeight = fontWeight,
            fontSize = fontSize.sp,
            color = color,
            fontFamily = fontFamily
        )
    }
}
