package com.doaamosallam.infinitystore.screen.register

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
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
import com.doaamosallam.infinitystore.compose.FullScreenLoading
import com.doaamosallam.infinitystore.compose.GenericButton
import com.doaamosallam.infinitystore.compose.Header
import com.doaamosallam.infinitystore.compose.ImageAuth
import com.doaamosallam.infinitystore.compose.Images
import com.doaamosallam.infinitystore.compose.RegisterTextButton
import com.doaamosallam.infinitystore.navigation.Screen
import kotlin.time.Duration

//State Hoisting
@Composable
fun RegisterUser(
    navController: NavController,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    val viewState by registerViewModel.uiState.collectAsState()
    // Create a SnackbarHostState
    val snackbarHostState = remember { SnackbarHostState() }

    // Separate state variables for errors
    var nameError by remember { mutableStateOf(false) }
    var phoneError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }

    // Separate state variables for name, phone, email, password, and confirmPassword
    var name by remember { mutableStateOf(viewState.name) }
    var phone by remember { mutableStateOf(viewState.phone) }
    var email by remember { mutableStateOf(viewState.email) }
    var password by remember { mutableStateOf(viewState.password) }
    var confirmPassword by remember { mutableStateOf(viewState.confirmPassword) }

    // LaunchedEffect for handling Snackbar based on loading and error states
    LaunchedEffect(viewState.loading, viewState.error) {
        if (viewState.loading) {
            snackbarHostState.showSnackbar(
                message = "Registration successful! Please verify your email and login.",
                duration = SnackbarDuration.Long
            )

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
        RegisterScreen(
            name = name,
            errorName = nameError,
            onNameChange = { newName ->
                name = newName
                registerViewModel.onNameChange(newName)
                nameError = name.isEmpty()
            },
            phone = phone,
            errorPhone = phoneError,
            onPhoneChange = { newPhone ->
                phone = newPhone
                registerViewModel.onPhoneChange(newPhone)
                phoneError = phone.length != 11
            },
            email = email,
            errorEmail = emailError,
            onEmailChange = { newEmail ->
                email = newEmail
                registerViewModel.onEmailChange(newEmail)
                emailError = !PatternsCompat.EMAIL_ADDRESS.matcher(newEmail).matches()
            },
            password = password,
            errorPassword = passwordError,
            onPasswordChange = { newPassword ->
                password = newPassword
                registerViewModel.onPasswordChange(newPassword)
                passwordError = password.length < 11 && password.matches(".*[A-Z].*".toRegex())
            },
            confirmPassword = confirmPassword,
            errorConfirmPassword = confirmPasswordError,
            onConfirmPasswordChange = { newConfirmPassword ->
                confirmPassword = newConfirmPassword
                registerViewModel.onConfirmPasswordChange(newConfirmPassword)
                confirmPasswordError = confirmPassword != password
            },
            onClickRegister = {
                if (!nameError && !phoneError && !emailError && !passwordError && !confirmPasswordError) {
                    registerViewModel.registerUser()
                    navController.navigate(Screen.Login.route)
                }
            },
            onClickLogin = { navController.navigate(Screen.Login.route) }
        )
    }
}

@Composable
fun RegisterScreen(
    name: String,
    onNameChange: (String) -> Unit,
    errorName: Boolean,
    phone: String,
    onPhoneChange: (String) -> Unit,
    errorPhone: Boolean,
    email: String,
    onEmailChange: (String) -> Unit,
    errorEmail: Boolean,
    password: String,
    onPasswordChange: (String) -> Unit,
    errorPassword: Boolean,
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    errorConfirmPassword: Boolean,
    onClickRegister: () -> Unit,
    onClickLogin: () -> Unit

) {
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

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
            isError = errorName,
            label = { Text(text = stringResource(id = R.string.enter_your_name)) },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.person_outline_24),
                    contentDescription = null
                )
                if (name.isNotEmpty()) {
                    IconButton(onClick = { onNameChange("") }) {
                        Icon(Icons.Filled.Clear, contentDescription = "Clear name")
                    }
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            visualTransformation = VisualTransformation.None
        )

        if (errorName) {
            Text(
                text = stringResource(R.string.your_name_is_not_valid),
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp),
            value = phone,
            onValueChange = { onPhoneChange(it) },
            isError = errorPhone,
            label = { Text(text = stringResource(R.string.enter_you_phone_number)) },
            trailingIcon = {

                Icon(
                    painter = painterResource(id = R.drawable.outline_phone_24),
                    contentDescription = null
                )
                if (phone.isNotEmpty()) {
                    IconButton(onClick = { onPhoneChange("") }) {
                        Icon(Icons.Filled.Clear, contentDescription = "Clear phone")

                    }
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            visualTransformation = VisualTransformation.None
        )
        if (errorPhone) {
            Text(
                text = stringResource(R.string.number_phone_must_be_11_number),
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

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
                imeAction = ImeAction.Next
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

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 20.dp),
            value = confirmPassword,
            onValueChange = { onConfirmPasswordChange(it) },
            isError = errorConfirmPassword,
            singleLine = true,
            label = { Text(text = stringResource(R.string.confirm_password)) },
            trailingIcon = {
                IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                    Icon(
                        painter = if (confirmPasswordVisible) painterResource(id = R.drawable.outline_lock_open_24)
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
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None
            else PasswordVisualTransformation()
        )
        if (errorConfirmPassword) {
            Text(
                text = "Confirm Password do not match",
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        GenericButton(
            onClick = onClickRegister,
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
                    onClick = onClickLogin,
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
    RegisterScreen(
        name = "",
        onNameChange = {},
        errorName = false,
        phone = "",
        onPhoneChange = {},
        errorPhone = false,
        email = "",
        onEmailChange = {},
        errorEmail = false,
        password = "",
        onPasswordChange = {},
        errorPassword = false,
        confirmPassword = "",
        onConfirmPasswordChange = {},
        errorConfirmPassword = false,
        onClickRegister = { /*TODO*/ }) {

    }
}