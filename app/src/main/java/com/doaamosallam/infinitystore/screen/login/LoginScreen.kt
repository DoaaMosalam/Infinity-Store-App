package com.doaamosallam.infinitystore.screen.login

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.util.PatternsCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.doaamosallam.infinitystore.R
import com.doaamosallam.infinitystore.compose.ForgetTextButton
import com.doaamosallam.infinitystore.compose.GenericButton
import com.doaamosallam.infinitystore.compose.Header
import com.doaamosallam.infinitystore.compose.ImageAuth
import com.doaamosallam.infinitystore.compose.Images
import com.doaamosallam.infinitystore.compose.RegisterTextButton
import com.doaamosallam.infinitystore.navigation.Screen

//State Hoisting
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginUser(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    val viewState by loginViewModel.uiState.collectAsState()

    // Create a SnackbarHostState
    val snackbarHostState = remember { SnackbarHostState() }

    // Separate state variables for errors
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }


    // Extract email and password from viewState
    var email by remember { mutableStateOf(viewState.email) }
    var password by remember { mutableStateOf(viewState.password) }

    LaunchedEffect(viewState.loading, viewState.error) {
        if (viewState.loading) {
            snackbarHostState.showSnackbar(
                message = "Login successful!",
                duration = SnackbarDuration.Long
            )
            navController.navigate(Screen.HomeScreen.route)
        }
        else if (viewState.error.isNotEmpty()) {
            snackbarHostState.showSnackbar(
                message = "Error: ${viewState.error}"
            )
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        LoginScreen(
            email = email,
            errorEmail = emailError,
            onEmailChange = { newEmail ->
                email = newEmail
                loginViewModel.onEmailChange(newEmail)
                emailError = !PatternsCompat.EMAIL_ADDRESS.matcher(newEmail).matches()
            },

            password = password,
            errorPassword = passwordError,
            onPasswordChange = { newPassword ->
                password = newPassword
                loginViewModel.onPasswordChange(newPassword)
                passwordError = password.length < 11 && password.matches(".*[A-Z].*".toRegex())
            },

            onClickLogin = {
                if (!emailError && !passwordError) {
                    loginViewModel.loginUser()
                    navController.navigate(Screen.HomeScreen.route)
                }
            },
            onClickRegister = {
                navController.navigate(Screen.Register.route)
            },
            OnClickForgetPassword = {
                navController.navigate(Screen.ForgetPassword.route)
            }
        )
    }
}

@Composable
private fun LoginScreen(
    email: String,
    errorEmail: Boolean,
    onEmailChange: (String) -> Unit,
    errorPassword: Boolean,
    password: String,
    onPasswordChange: (String) -> Unit,
    onClickLogin: () -> Unit,
    onClickRegister: () -> Unit,
    OnClickForgetPassword: () -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }
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
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp),
            value = email,
            onValueChange = { onEmailChange(it) },
            isError = errorEmail,
            label = { Text(text = stringResource(id = R.string.enter_your_email)) },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.outline_email_24),
                    contentDescription = null
                )
                if (email.isNotEmpty()) {
                    IconButton(onClick = { onEmailChange("") }) {
                        Icon(Icons.Filled.Clear, contentDescription = "Clear email")
                    }
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            visualTransformation = VisualTransformation.None
        )
        if (errorEmail) {
            Text(
                text = stringResource(R.string.not_a_valid_email_address_should_be_your_email_com),
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp),
            value = password,
            onValueChange = { onPasswordChange(it) },
            isError = errorPassword,
            singleLine = true,
            label = { Text(text = stringResource(id = R.string.enter_your_password)) },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = if (passwordVisible) painterResource(id = R.drawable.outline_lock_open_24)
                        else painterResource(id = R.drawable.outline_lock_24),
                        contentDescription = null
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (passwordVisible) VisualTransformation.None
            else PasswordVisualTransformation()
        )
        if (errorPassword) {
            Text(
                text = stringResource(R.string.password_must_be_11_number),
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        GenericButton(
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
        onEmailChange = {/*TODO*/ },
        errorEmail = false,
        password = "",
        onPasswordChange = {/*TODO*/ },
        errorPassword = false,
        onClickLogin = { /*TODO*/ },
        onClickRegister = { /*TODO*/ }) {
    }
}
