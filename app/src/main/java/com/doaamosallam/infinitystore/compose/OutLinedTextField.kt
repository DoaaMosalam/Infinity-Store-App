package com.doaamosallam.infinitystore.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.doaamosallam.infinitystore.R

@Composable

fun OutLinedTextField(value:String,
                      onValueChange: (String) -> Unit

                      ){
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp),
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(R.string.enter_you_phone_number)) },
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.outline_phone_24),
                contentDescription = null
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Next
        ),
        visualTransformation = VisualTransformation.None
    )
}