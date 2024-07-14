package com.doaamosallam.infinitystore.screen.forget_password_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.util.PatternsCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.doaamosallam.infinitystore.R
import com.doaamosallam.infinitystore.compose.AuthButton
import com.doaamosallam.infinitystore.compose.ClickableImage
import com.doaamosallam.infinitystore.util.Screen
import com.doaamosallam.infinitystore.viewmodel.forget_password.ForgetPasViewState
import com.doaamosallam.infinitystore.viewmodel.forget_password.ForgetPasswordIntent
import com.doaamosallam.infinitystore.viewmodel.forget_password.ForgetPasswordViewModel
import kotlinx.coroutines.launch

// state hoisting
@Composable
fun ForgetPassword(
    navController: NavController,
    forgetPasswordViewModel: ForgetPasswordViewModel = hiltViewModel()

) {
    val viewState by forgetPasswordViewModel.forgetViewState.collectAsState()
    // Separate state variables for errors
    var emailError by remember { mutableStateOf(false) }
    // Extract email and password from viewState
    var email =
        if (viewState is ForgetPasViewState.Content) (viewState as ForgetPasViewState.Content).email else ""

    // Create a SnackbarHostState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()


    ForgetScreen(
        email = email,
        emailError = emailError,
        onEmailChanged = { newEmail ->
            forgetPasswordViewModel.onEmailChange(newEmail)
            emailError = !PatternsCompat.EMAIL_ADDRESS.matcher(newEmail).matches()
        },
        onClickSend = {
            // Trigger login event
            forgetPasswordViewModel.handleIntent(ForgetPasswordIntent.ForgetPassword(email))
            // Show a snackbar
            if (!emailError && email.isNotEmpty()) {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Check your email, you will receive a link to create a new password via email")
                    navController.navigate(Screen.Login.route)
                }

            }
        },
        onClickBack = { navController.popBackStack() },
        snackbarHostState = snackbarHostState
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ForgetScreen(
    email: String,
    emailError: Boolean,
    onEmailChanged: (String) -> Unit,
    onClickSend: () -> Unit,
    onClickBack: () -> Unit,
    snackbarHostState: SnackbarHostState
) {
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        Column(modifier = Modifier.padding(top = 30.dp, start = 10.dp, end = 10.dp)) {
            ClickableImage(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = null,
                onClick = onClickBack
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.forgot_password),
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = stringResource(R.string.please_enter_your_email_address_you_will_receive_a_link_to_create_a_new_password_via_email),
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = onEmailChanged,
                label = { Text("Email") },
                isError = emailError,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_email_24),
                        contentDescription = null
                    )
                    if (email.isNotEmpty()) {
                        IconButton(onClick = { onEmailChanged("") }) {
                            Icon(Icons.Filled.Clear, contentDescription = "Clear email")
                        }
                    }
                }
            )

            if (emailError) {
                Text(
                    text = stringResource(R.string.not_a_valid_email_address_should_be_your_email_com),
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            AuthButton(
                onClick = onClickSend,
                buttonText = "Send",
                buttonColor = Color.White,
                textColor = colorResource(id = R.color.primary_color)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForgetPasswordPreview() {
    ForgetScreen(
        email = "",
        emailError = false,
        onEmailChanged = { /* TODO: Handle email change */ },
        onClickSend = { /* TODO: Implement sending logic */ },
        onClickBack = { /* TODO: Navigate back */ },
        snackbarHostState = remember { SnackbarHostState() }
    )
}