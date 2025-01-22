package com.doaamosallam.infinitystore.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.doaamosallam.infinitystore.ui.theme.PrimaryColor

@Composable
fun ProfileDetailRow(
    onClickChangeItem: () -> Unit,
    painter: Painter,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .clickable { onClickChangeItem() }
            .fillMaxWidth()
            .padding(vertical = 1.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = PrimaryColor
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 30.dp)

        ) {
            TextGeneral(
                title = label,
                fontSize = 18.sp,
//                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp),
                fontFamily = FontFamily.Default,
                color = MaterialTheme.colorScheme.onBackground
            )

            TextGeneral(
                title = value,
                fontSize = 16.sp,
//                color = Color.Gray,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(bottom = 8.dp),
                fontFamily = FontFamily.Default,
                color = MaterialTheme.colorScheme.onBackground

            )
        }
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Color.Gray
        )
    }
}