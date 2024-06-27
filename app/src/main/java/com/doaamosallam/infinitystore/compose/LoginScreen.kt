package com.doaamosallam.infinitystore.compose
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.doaamosallam.domain.models.Login
import com.doaamosallam.infinitystore.R
import com.doaamosallam.infinitystore.ui.theme.PrimaryColor
import com.doaamosallam.infinitystore.ui.theme.fontName
import com.doaamosallam.infinitystore.ui.theme.playWriteEsFont
import com.doaamosallam.infinitystore.ui.theme.playWriteFont
import com.doaamosallam.infinitystore.util.RequestStatus
import com.doaamosallam.infinitystore.viewmodel.user.Login.LoginViewModel
import kotlinx.coroutines.flow.merge

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
        Image(painter = painterResource(id = R.drawable.logo)
            , contentDescription ="Logo Image",
            modifier = Modifier
            .padding(top = 50.dp)
            .size(120.dp)
        )

        Text(
            text = "Welcome To Infinity Store",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 10.dp),
            fontSize = 20.sp,
            fontFamily = playWriteFont,
            color = PrimaryColor
        )

        Text(
            text = "Sign In To Continue",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 9.dp),
            fontSize = 16.sp,
            fontFamily = FontFamily.Default
        )
    }


//    val emailState = remember { mutableStateOf("") }
//    val passwordState = remember { mutableStateOf("") }
//    val loginData = "$emailState, $passwordState"
//
//    val loginState by viewModel.loginState.collectAsState(initial = RequestStatus.Success(loginData))
//    val errorMessage by viewModel.errorMessage.collectAsState(initial = "")
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(horizontal = 16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        OutlinedTextField(
//            value = emailState.value,
//            onValueChange = { emailState.value = it },
//            label = { Text("Email") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 16.dp)
//        )
//
//        OutlinedTextField(
//            value = passwordState.value,
//            onValueChange = { passwordState.value = it },
//            label = { Text("Password") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 16.dp),
//            visualTransformation = PasswordVisualTransformation()
//        )
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