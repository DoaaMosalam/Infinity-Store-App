package com.doaamosallam.infinitystore.compose

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.doaamosallam.domain.models.profile.ImagesUser
import com.doaamosallam.infinitystore.screen.profile.ProfileViewModel

@Composable
 fun selectedProfileImage(viewModel: ProfileViewModel): ManagedActivityResultLauncher<String, Uri?> {
    // Launch a gallery intent using the Activity Result API
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            // Save the selected image to the Room database
            val imageUser = ImagesUser(id = 1, imageUri = it.toString())
            viewModel.addImages(imageUser)
        }
    }

    // Fetch the saved profile image whenever the screen is loaded
    LaunchedEffect(Unit) {
        viewModel.loadImage()
    }
    return galleryLauncher
}