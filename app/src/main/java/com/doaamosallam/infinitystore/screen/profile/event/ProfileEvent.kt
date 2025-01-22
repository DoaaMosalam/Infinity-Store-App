package com.doaamosallam.infinitystore.screen.profile.event

import com.doaamosallam.domain.models.profile.ImagesUser

sealed class ProfileEvent {
    data class AddImages(val imageUri: ImagesUser) : ProfileEvent()
    data class GetImages(val imageUri: ImagesUser) : ProfileEvent()

    data class GetName(val name: String) : ProfileEvent()
    data class GetEmail(val email: String) : ProfileEvent()
    data class GetPhone(val phone: String) : ProfileEvent()
    data class GetPassword(val password: String) : ProfileEvent()
    data class UpdatePasswordInDb(val email: String, val newPassword: String) : ProfileEvent()

    data class SetLoading(val isLoading: Boolean) : ProfileEvent()
    data class SetError(val message: String) : ProfileEvent()
    data class SetSuccess(val message: String) : ProfileEvent()

}