package com.doaamosallam.infinitystore.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@Composable
    fun AuthTextField(
        modifier: Modifier = Modifier,
        value:String,
        onValueChange:(String) -> Unit,
        label:String,
        trailingIconResId: Int,
        keyboardOptions: KeyboardOptions,
        visualTransformation: VisualTransformation = VisualTransformation.None
    ) {

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp),
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        singleLine = true,
        trailingIcon = {
            Icon(
                painter = painterResource(id = trailingIconResId),
                contentDescription = null
            )
        },
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation
    )
}
