package com.doaamosallam.infinitystore.screen.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.doaamosallam.domain.models.profile.ImagesUser
import com.doaamosallam.domain.usecase.ProfileUseCase
import com.doaamosallam.domain.usecase.RegisterUseCase
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
    private val profileUseCase: ProfileUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> get() = _uiState

    init {
        loadImage()

       getName(uiState.value.name)

        getEmail()
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

            is ProfileEvent.GetPassword -> _uiState.value.copy(
                password = event.password,
                loading = false,
                success = true
            )

            is ProfileEvent.GetPhone -> _uiState.value.copy(
                phone = event.phone,
                loading = false,
                success = true
            )

            is ProfileEvent.GetName -> _uiState.value.copy(
                name = event.name,
                loading = false,
                success = true
            )

            is ProfileEvent.GetEmail -> _uiState.value.copy(
                email = event.email,
                loading = false,
                success = true
            )

            is ProfileEvent.UpdatePasswordInDb -> {
                viewModelScope.launch {
                    try {
                        emitEvent(ProfileEvent.SetLoading(true))
                        registerUseCase.updatePassword(event.email, event.newPassword)
                        emitEvent(ProfileEvent.SetSuccess("Password updated successfully"))
                    } catch (e: Exception) {
                        emitEvent(ProfileEvent.SetError(e.message ?: "An error occurred"))
                    } finally {
                        emitEvent(ProfileEvent.SetLoading(false))
                    }
                }
                _uiState.value
            }

            is ProfileEvent.SetLoading -> _uiState.value.copy(
                loading = event.isLoading
            )

            is ProfileEvent.SetError -> _uiState.value.copy(
                loading = false,
                error = event.message,
                success = false
            )

            is ProfileEvent.SetSuccess -> _uiState.value.copy(
                loading = false,
                success = true
            )

        }
    }

    fun addImages(images: ImagesUser) = viewModelScope.launch {
        try {
            emitEvent(ProfileEvent.SetLoading(true))
            profileUseCase.addImageToProfile(listOf(images))
            emitEvent(ProfileEvent.AddImages(images))
            loadImage() // Reload the images after adding
        } catch (e: Exception) {
            emitEvent(ProfileEvent.SetError(e.message ?: "An error occurred"))
        }
    }

    fun loadImage() = viewModelScope.launch {
        try {
            emitEvent(ProfileEvent.SetLoading(true))
            profileUseCase.getImageFromProfile().collectLatest { result ->
                val image = result ?: ImagesUser(0, "") // Handle potential null values
                emitEvent(ProfileEvent.GetImages(image))
            }
        } catch (e: Exception) {
            emitEvent(ProfileEvent.SetError(e.message ?: "An error occurred"))
        }
    }


    fun getName(name:String) = viewModelScope.launch {
        try {
            emitEvent(ProfileEvent.SetLoading(true))
//            val result = registerUseCase.getUserByName(uiState.value.name)
//            emitEvent(
//                ProfileEvent.GetName(
//                    result!!.name
//                )
//            )
            val user = registerUseCase.getUserByName(name)
            if (user != null){
                emitEvent(ProfileEvent.GetName(user.name))

            }else{
                emitEvent(ProfileEvent.SetError("User not found"))
            }
            Log.d("ProfileViewModel", "User name fetched: ${user?.name}")

            emitEvent(ProfileEvent.SetLoading(false))

        }catch (e:Exception){
            emitEvent(ProfileEvent.SetError(e.message ?: "An error occurred"))
        }
    }

    fun getEmail() = viewModelScope.launch {
        try {
            emitEvent(ProfileEvent.SetLoading(true))
            val result = registerUseCase.getUserByName(uiState.value.name)
            emitEvent(
                ProfileEvent.GetEmail(
                    result!!.email
                ))
            emitEvent(ProfileEvent.SetSuccess("User email fetched successfully"))

        }catch (e:Exception){
            emitEvent(ProfileEvent.SetError(e.message ?: "An error occurred"))
        }
    }

}