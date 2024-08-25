package com.doaamosallam.infinitystore.screen.setting

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.doaamosallam.infinitystore.R
import com.doaamosallam.infinitystore.compose.GenericButton
import com.doaamosallam.infinitystore.compose.SpacerGeneral
import com.doaamosallam.infinitystore.compose.TextGeneral
import com.doaamosallam.infinitystore.compose.TopBarScreen
import com.doaamosallam.infinitystore.compose.selectedProfileImage
import com.doaamosallam.infinitystore.navigation.BottomNavigationBar
import com.doaamosallam.infinitystore.screen.profile.ProfileViewModel
import com.doaamosallam.infinitystore.screen.profile.state.ProfileUiState
import com.doaamosallam.infinitystore.ui.theme.Merri
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingContainer(navController: NavController) {

    val viewModel: ProfileViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    val galleryLauncher = selectedProfileImage(viewModel)

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        SettingScreen(
            onClickBack = { navController.popBackStack() },
            onImageClick = {galleryLauncher.launch("image/*") },
            uiState = uiState,
            onName = "Doaa Mosallam",
            onEmail = "Doaa@yahoo.com",
            onClickLogOut = {},
            onConfirmPassword = {},

            )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    onClickBack: () -> Unit,
    onImageClick: () -> Unit,
    uiState: ProfileUiState,
    onName: String,
    onEmail: String,
    onClickLogOut: () -> Unit,
    onConfirmPassword: () -> Unit
) {
    var isDarkTheme by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("English") }
    val languages = listOf("English", "Arabic")
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState()
    val showBottomSheet: () -> Unit = {
        scope.launch { bottomSheetState.show() }
    }

    Column(
        modifier = Modifier
            .padding(top = 20.dp, start = 10.dp, end = 10.dp)
            .fillMaxSize()
    ) {
        TopBarScreen(
            modifier = Modifier.padding(top = 10.dp),
            onClickBack = onClickBack,
            text = stringResource(R.string.setting),
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            fontFamily = Merri,
            color = Color.DarkGray
        )

        TextGeneral(
            title = "Adjust Settings",
            modifier = Modifier.padding(start = 20.dp),
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Default,
            color = Color.Gray
        )

        SpacerGeneral(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                    .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Profile picture and name
                SpacerGeneral(modifier = Modifier.height(10.dp))
                    Image(
                        painter = if (uiState.images.imageUri.isNotEmpty()) {
                            rememberAsyncImagePainter(uiState.images.imageUri)
                        } else {
                            painterResource(id = R.drawable.person_outline_24)
                        },
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable(onClick = onImageClick)
                            .size(100.dp)
                            .background(Color.Gray, CircleShape)
                    )
                SpacerGeneral(modifier = Modifier.height(30.dp))

                        TextGeneral(
                            title = onName,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontFamily = FontFamily.Default,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        TextGeneral(
                            title = onEmail,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray,
                            fontFamily = FontFamily.Default,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Language Selector
                TextGeneral(
                    title = "Language",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp),
                    fontFamily = FontFamily.Default,
                    color = Color.Black
                )
                DropdownMenu(
                    expanded = false, // Set this to true if you want the dropdown to be visible
                    onDismissRequest = { /* Handle dismiss */ }
                ) {
                    languages.forEach { language ->
                        DropdownMenuItem(
                            text = { Text(text = language) },
                            onClick = { selectedLanguage = language }
                        )
                    }
                }

                // Dark Mode Switch
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { isDarkTheme = !isDarkTheme }
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "Dark Mode",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Switch(checked = isDarkTheme, onCheckedChange = { isDarkTheme = it })
                }

                // Change Password
                Spacer(modifier = Modifier.height(24.dp))
                GenericButton(
                    onClick = showBottomSheet,
                    buttonText = "Change Password",
                    buttonColor = Color.White,
                    textColor = colorResource(id = R.color.primary_color),
                    modifier = Modifier.fillMaxWidth()
                )

                // Log Out Button
                Spacer(modifier = Modifier.height(24.dp))
                GenericButton(
                    onClick = { onClickLogOut() },
                    buttonText = "Log Out",
                    buttonColor = Color.White,
                    textColor = colorResource(id = R.color.primary_color),
                    modifier = Modifier.fillMaxWidth()
                )
    }

    // Bottom Sheet for Changing Password
    if (bottomSheetState.isVisible) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = { scope.launch { bottomSheetState.hide() } }
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Change Password", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                // Add password input fields here
                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Handle password change */ },
                    label = { Text(stringResource(R.string.new_password)) },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = { onConfirmPassword() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Confirm")
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewSettingScreen() {
    SettingScreen(
        onClickBack = {},
        onImageClick = {},
        uiState = ProfileUiState(),
        onClickLogOut = {},
        onConfirmPassword = {},
        onName = "Doaa Mosallam",
        onEmail = "Doaa@yahoo.com"
    )
}
