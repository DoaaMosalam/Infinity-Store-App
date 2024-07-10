package com.doaamosallam.infinitystore.screen

import android.annotation.SuppressLint
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.doaamosallam.infinitystore.R
import com.doaamosallam.infinitystore.compose.AuthButton
import com.doaamosallam.infinitystore.compose.ForgetTextButton
import com.doaamosallam.infinitystore.compose.Header
import com.doaamosallam.infinitystore.compose.ImageAuth
import com.doaamosallam.infinitystore.compose.Images
import com.doaamosallam.infinitystore.compose.RegisterTextButton
import com.doaamosallam.infinitystore.util.AppDestination
import com.doaamosallam.infinitystore.viewmodel.Login.LoginIntent
import com.doaamosallam.infinitystore.viewmodel.Login.LoginViewModel
import com.doaamosallam.infinitystore.viewmodel.Login.LoginViewState

//State Hoisting
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginUser(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val viewState by loginViewModel.viewState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    // Extract email and password from viewState
    val email =
        if (viewState is LoginViewState.Content) (viewState as LoginViewState.Content).email else ""
    val password =
        if (viewState is LoginViewState.Content) (viewState as LoginViewState.Content).password else ""
    LaunchedEffect(viewState) {
        if (viewState is LoginViewState.Success) {
            snackbarHostState.showSnackbar("Login successful!")
            navController.navigate(AppDestination.HomeScreen)
        }

    }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        LoginScreen(
            email = email,
            onEmailChange = loginViewModel::onEmailChange,
            password = password,
            onPasswordChange = loginViewModel::onPasswordChange,
            onClickLogin = {
                // Trigger login event
                loginViewModel.handleIntent(LoginIntent.Login(email, password))
            },
            onClickRegister = {
                navController.navigate(AppDestination.RegisterScreen)
            },
            OnClickForgetPassword = {
                navController.navigate(AppDestination.ForgetPassword)
            }
        )
    }
}

@Composable
private fun LoginScreen(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onClickLogin: () -> Unit,
    onClickRegister: () -> Unit,
    OnClickForgetPassword:()->Unit
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
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp, start = 10.dp, end = 10.dp, bottom = 100.dp),
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
                    .padding(top = 190.dp, start = 10.dp, end = 10.dp),
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
                    imeAction = ImeAction.Done
                )
            )
        }
        Spacer(modifier = Modifier.height(30.dp))

        AuthButton(
            onClick = onClickLogin,
            buttonText = stringResource(id = R.string.login),
            buttonColor = Color.White, // Set your desired background color
            textColor = colorResource(id = R.color.primary_color) // Set your desired text color
        )
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 16.dp)
        ) {
            ForgetTextButton(
                text = stringResource(R.string.forget_password),
                onClick = OnClickForgetPassword,
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

                Text(text = stringResource(R.string.don_t_have_a_account))
                Spacer(modifier = Modifier.width(8.dp))
                RegisterTextButton(
                    text = stringResource(id = R.string.register),
                    onClick = onClickRegister,
                    modifier = Modifier.height(16.dp)
                )
                Images(
                    painter = painterResource(id = R.drawable.baseline_arrow_forward_24),
                    description = null.toString()
                )
            }

        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
   LoginScreen(
       email = "",
       onEmailChange ={/*TODO*/} ,
       password = "",
       onPasswordChange ={/*TODO*/} ,
       onClickLogin = { /*TODO*/ },
       onClickRegister = { /*TODO*/ }) {
   }
}
