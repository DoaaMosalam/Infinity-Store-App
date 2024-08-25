package com.doaamosallam.infinitystore.screen.profile

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.doaamosallam.domain.models.profile.ImagesUser
import com.doaamosallam.infinitystore.R
import com.doaamosallam.infinitystore.compose.ProfileDetailRow
import com.doaamosallam.infinitystore.compose.SpacerGeneral
import com.doaamosallam.infinitystore.compose.TextGeneral
import com.doaamosallam.infinitystore.compose.TopBarScreen
import com.doaamosallam.infinitystore.compose.selectedProfileImage
import com.doaamosallam.infinitystore.screen.profile.state.ProfileUiState
import com.doaamosallam.infinitystore.ui.theme.Merri



@Composable
fun ProfileContainer(navController: NavController) {
    val viewModel: ProfileViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    val galleryLauncher = selectedProfileImage(viewModel)


    ProfileScreen(
        onClickBack = { navController.popBackStack() },
        onImageClick = { galleryLauncher.launch("image/*") }, // Trigger the gallery
        uiState = uiState,
        onName = "Doaa Mosallam",
        onEmail = "Email",
        onEmailValue = "Doaa@yahoo.com",
        onGender = "Gender",
        onGenderValue = "Female",
        onBirthday = "Birthday",
        onBirthdayValue = "6/9/1991",
        onPhoneNumber = "Phone Number",
        onPhoneNumberValue = "012345678900",
        onPassword = "Password",
        onPasswordValue = "Doaa0987654321"
    )
}



@Composable
fun ProfileScreen(
    onClickBack: () -> Unit,
    onImageClick: () -> Unit,
    uiState: ProfileUiState,
    onName: String,
    onEmail: String,
    onEmailValue: String,
    onGender: String,
    onGenderValue: String,
    onBirthday: String,
    onBirthdayValue: String,
    onPhoneNumber: String,
    onPhoneNumberValue: String,
    onPassword: String,
    onPasswordValue: String
) {
    Column(
        modifier = Modifier
            .padding(top = 20.dp, start = 10.dp, end = 10.dp)
            .fillMaxSize()
    ) {
        TopBarScreen(
            modifier = Modifier.padding(top = 10.dp),
            onClickBack = onClickBack,
            text = stringResource(R.string.profile),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            fontFamily = Merri,
            color = Color.DarkGray
        )
        TextGeneral(
            title = stringResource(R.string.personal_information),
            modifier = Modifier.padding(start = 20.dp),
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Default,
            color = Color.Gray
        )
        SpacerGeneral(modifier = Modifier.height(5.dp))
        Card(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                    .fillMaxSize()
            ) {
                // Profile picture and name
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Display the selected image or a default placeholder
                    Image(
                        painter = if (uiState.images.imageUri.isNotEmpty()) {
                            rememberAsyncImagePainter(uiState.images.imageUri)
                        } else {
                            painterResource(id = R.drawable.person_outline_24)
                        },
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clickable(onClick = onImageClick)
                            .size(80.dp)
                            .background(Color.Gray, CircleShape)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
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
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray,
                            fontFamily = FontFamily.Default,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Profile details
                ProfileDetailRow(
                    painter = painterResource(id = R.drawable.baseline_transgender_24),
                    label = onGender,
                    value = onGenderValue
                )
                ProfileDetailRow(
                    painter = painterResource(id = R.drawable.baseline_date_range_24),
                    label = onBirthday,
                    value = onBirthdayValue
                )
                ProfileDetailRow(
                    painter = painterResource(id = R.drawable.outline_email_24),
                    label = onEmail,
                    value = onEmailValue
                )
                ProfileDetailRow(
                    painter = painterResource(id = R.drawable.outline_phone_24),
                    label = onPhoneNumber,
                    value = onPhoneNumberValue
                )
                ProfileDetailRow(
                    painter = painterResource(id = R.drawable.outline_lock_24),
                    label = onPassword,
                    value = onPasswordValue
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewProfileScreen() {
    ProfileScreen(
        onClickBack = {},
        onImageClick = {},
        uiState = ProfileUiState(),
        onName = "Doaa Mosallam",
        onEmail = "Doaa@yahoo.com",
        onEmailValue = "",
        onGender = "Male",
        onGenderValue = "",
        onBirthday = "12-12-2000",
        onBirthdayValue = "",
        onPhoneNumber = "(307) 555-0133",
        onPhoneNumberValue = "",
        onPassword = "********",
        onPasswordValue = ""
    )
}

