package com.doaamosallam.infinitystore.compose.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.doaamosallam.infinitystore.R
import com.doaamosallam.infinitystore.compose.AuthButton
import com.doaamosallam.infinitystore.compose.Header
import com.doaamosallam.infinitystore.compose.ImageAuth
import com.doaamosallam.infinitystore.compose.Images
import com.doaamosallam.infinitystore.compose.RegisterTextButton

@Composable
fun RegisterUser(){
    // Separate state variables for name, phone, email, password, and confirmPassword
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    RegisterScreen(
       name = name,
        onNameChange = {name=it},
        phone= phone,
        onPhoneChange = {phone=it},
        email=email,
        onEmailChange = {email=it},
        password=password,
        onPasswordChange = {password=it},
        confirmPassword = confirmPassword,
        onConfirmPasswordChange = {confirmPassword=it},
        onClickRegister = {
//            TODO()
        }

    )
}

@Composable
private fun RegisterScreen(
    name:String,
    onNameChange:(String)->Unit,
    phone:String,
    onPhoneChange:(String)->Unit,
    email:String,
    onEmailChange:(String)->Unit,
    password:String,
    onPasswordChange:(String)->Unit,
    confirmPassword:String,
    onConfirmPasswordChange:(String)->Unit,
    onClickRegister:()->Unit

){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageAuth(
            painterResource(id = R.drawable.logo),
            stringResource(R.string.logo_image)
        )

        Header(
            title = stringResource(R.string.let_s_get_started),
            subtitle = stringResource(R.string.create_an_new_account)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 10.dp, end = 10.dp),
            value = name,
            onValueChange = { onNameChange(it) },
            label = { Text(text = stringResource(id = R.string.enter_your_name)) },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.outline_person_24),
                    contentDescription = null
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            visualTransformation = VisualTransformation.None
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp),
            value = phone,
            onValueChange = { onPhoneChange(it) },
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

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp),
            value = email,
            onValueChange = { onEmailChange(it) },
            label = { Text(text = stringResource(id = R.string.enter_your_email)) },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.outline_email_24),
                    contentDescription = null
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            visualTransformation = VisualTransformation.None
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp),
            value = password,
            onValueChange = { onPasswordChange(it) },
            singleLine = true,
            label = { Text(text = stringResource(id = R.string.enter_your_password)) },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.outline_lock_24),
                    contentDescription = null
                )
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            )
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 20.dp),
            value = confirmPassword,
            onValueChange = { onConfirmPasswordChange(it) },
            singleLine = true,
            label = { Text(text = stringResource(R.string.confirm_password)) },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.outline_lock_24),
                    contentDescription = null
                )
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )

        AuthButton(
            onClick = { },
            buttonText = stringResource(id = R.string.register),
            buttonColor = Color.White, // Set your desired background color
            textColor = colorResource(id = R.color.primary_color) // Set your desired text color
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "-----------------------------------OR-------------------------------")

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(text = "Have a account?")
                Spacer(modifier = Modifier.width(8.dp))
                RegisterTextButton(
                    text = stringResource(id = R.string.login),
                    onClick = {onClickRegister },
                    modifier = Modifier.height(16.dp)
                )
                Images(
                    painter = painterResource(id = R.drawable.baseline_arrow_forward_24),
                    description = ""
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
   RegisterUser()
}