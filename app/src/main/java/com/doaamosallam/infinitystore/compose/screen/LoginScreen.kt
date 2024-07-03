package com.doaamosallam.infinitystore.compose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.AbsoluteAlignment
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
import androidx.lifecycle.ViewModel
import com.doaamosallam.infinitystore.R
import com.doaamosallam.infinitystore.compose.AuthButton
import com.doaamosallam.infinitystore.compose.ForgetTextButton
import com.doaamosallam.infinitystore.compose.Header
import com.doaamosallam.infinitystore.compose.ImageAuth
import com.doaamosallam.infinitystore.compose.Images
import com.doaamosallam.infinitystore.compose.RegisterTextButton
import com.doaamosallam.infinitystore.viewmodel.Login.LoginIntent
import com.doaamosallam.infinitystore.viewmodel.Login.LoginViewModel
import com.doaamosallam.infinitystore.viewmodel.Login.LoginViewState


@Composable
fun LoginScreen(
//    viewModel: LoginViewModel = hiltViewModel()
//    viewModel: LoginViewModel = viewModel()
) {
//    val viewState by viewModel.viewState.collectAsState()

    // Separate state variables for email and password
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
                visualTransformation = VisualTransformation.None

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
            onClick = { },
//                viewModel.handleIntent(LoginIntent.Login(email, password)) },
            buttonText = stringResource(id = R.string.login),
            buttonColor = Color.White, // Set your desired background color
            textColor = colorResource(id = R.color.primary_color) // Set your desired text color
        )
        Spacer(modifier = Modifier.height(20.dp))
//        when (viewState) {
//            is LoginViewState.Loading -> Text(text = "Loading...")
//            is LoginViewState.Success -> Text(text = "Login Successful: ${(viewState as LoginViewState.Success).login.email}")
//            is LoginViewState.Error -> Text(text = (viewState as LoginViewState.Error).message, color = Color.Red)
//            else -> {}
//        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 16.dp)
        ) {
            ForgetTextButton(
                text = stringResource(R.string.forget_password),
                onClick = { /*TODO*/ },
                modifier = Modifier.height(16.dp)
            )
        }
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

            Text(text = stringResource(R.string.don_t_have_a_account) )
                Spacer(modifier = Modifier.width(8.dp))
            RegisterTextButton(
                text = stringResource(id = R.string.register) ,
                onClick = { /*TODO*/ },
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
fun PreviewLoginScreen() {
    LoginScreen()
}