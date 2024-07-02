package com.doaamosallam.infinitystore.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.doaamosallam.infinitystore.R
import com.doaamosallam.infinitystore.modifier.AuthButton
import com.doaamosallam.infinitystore.modifier.Header
import com.doaamosallam.infinitystore.modifier.ImageAuth

@Composable
fun LoginScreen(
//    viewModel: LoginViewModel = viewModel()

) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ImageAuth(
            painterResource(id = R.drawable.logo),
            stringResource(R.string.logo_image)
        )

        Header(
            title = stringResource(id = R.string.welcome_to_infinity),
            subtitle = stringResource(id = R.string.sign_in_to_continue)
        )
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        )
        {
            // Separate state variables for email and password
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp, start = 10.dp, end = 10.dp, bottom = 100.dp),
                value = email,
                onValueChange = { email = it },
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

                )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 190.dp, start = 10.dp, end = 10.dp),
                value = password,
                onValueChange = { password = it },
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
                    imeAction = ImeAction.Done
                )
            )
        }
        Spacer(modifier = Modifier.height(30.dp))

        AuthButton(
            onClick = { /* Handle login action here */ },
            buttonText = stringResource(id = R.string.login),
            buttonColor = Color.White, // Set your desired background color
            textColor = colorResource(id = R.color.primary_color) // Set your desired text color
        )
    }

//    val emailState = remember { mutableStateOf("") }
//    val passwordState = remember { mutableStateOf("") }
//    val loginData = "$emailState, $passwordState"
//
//    val loginState by viewModel.loginState.collectAsState(initial = RequestStatus.Success(loginData))
//    val errorMessage by viewModel.errorMessage.collectAsState(initial = "")
//
//
//        Button(
//            onClick = {
//                val login = Login(email = emailState.value, password = passwordState.value)
//                viewModel.login(login)
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 16.dp)
//        ) {
//            Text("Login")
//        }
//
//        if (loginState is RequestStatus.Error) {
//            Text(errorMessage)
//        }
//    }
}



@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}