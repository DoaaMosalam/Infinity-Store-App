package com.doaamosallam.infinitystore.screen.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doaamosallam.domain.models.profile.ImagesUser
import com.doaamosallam.domain.usecase.ProfileUseCase
import com.doaamosallam.infinitystore.screen.profile.event.ProfileEvent
import com.doaamosallam.infinitystore.screen.profile.state.ProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> get() = _uiState

//    init {
//        loadProfileImage() // Load the image when the ViewModel is initialized
//    }
//
//    fun addImages(imageUser: ImagesUser) {
//        viewModelScope.launch {
//            profileUseCase.addImageToProfile(imageUser)
//            Log.d("profileViewModel", "Saved Image URI: ${imageUser.imageUri}")
//            loadProfileImage() // Reload the image after saving
//        }
//    }
//
//    fun loadProfileImage() {
//        viewModelScope.launch {
//            profileUseCase.getImageFromProfile().collectLatest { imagesUser ->
//                imagesUser?.let {
//                    _uiState.value = _uiState.value.copy(images = it)
//                }
//                Log.d("profileViewModel", "Loaded Image URI: ${uiState.value.images.imageUri}")
//
//            }
//        }
//    }

    init {
        loadImage()
    }

    fun addImages(images: ImagesUser) = viewModelScope.launch {
        try {
            emitEvent(ProfileEvent.IsLoading(true))
            profileUseCase.addImageToProfile(listOf(images))
            emitEvent(ProfileEvent.AddImages(images))
            loadImage() // Reload the images after adding
        } catch (e: Exception) {
            emitEvent(ProfileEvent.OnError(e.message ?: "An error occurred"))
        }
    }

    fun loadImage() = viewModelScope.launch {
        try {
            emitEvent(ProfileEvent.IsLoading(true))
             profileUseCase.getImageFromProfile().collectLatest { result ->
                 val image = result ?: ImagesUser(0,"") // Handle potential null values
                 emitEvent(ProfileEvent.GetImages(image))
            }
        } catch (e: Exception) {
            emitEvent(ProfileEvent.OnError(e.message ?: "An error occurred"))
        }
    }

    private fun emitEvent(event: ProfileEvent) {
        _uiState.value = when (event) {
            is ProfileEvent.AddImages -> _uiState.value.copy(
                images = event.imageUri,
                loading = false,
                success = true
            )
            is ProfileEvent.GetImages -> _uiState.value.copy(
                images = event.imageUri,
                loading = false,
                success = true
            )
            is ProfileEvent.IsLoading -> _uiState.value.copy(
                loading = event.isLoading
            )
            is ProfileEvent.OnError -> _uiState.value.copy(
                loading = false,
                error = event.message,
                success = false
            )
            is ProfileEvent.OnSuccess -> _uiState.value.copy(
                loading = false,
                success = true
            )
        }
    }
}