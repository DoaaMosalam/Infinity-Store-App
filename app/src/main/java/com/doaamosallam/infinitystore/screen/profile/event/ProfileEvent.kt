package com.doaamosallam.infinitystore.screen.profile.event

import com.doaamosallam.domain.models.profile.ImagesUser

sealed class ProfileEvent {
    data class AddImages(val imageUri: ImagesUser) : ProfileEvent()
    data class GetImages(val imageUri: ImagesUser) : ProfileEvent()
    data class IsLoading(val isLoading: Boolean) : ProfileEvent()
    data class OnError(val message: String) : ProfileEvent()
    data class OnSuccess(val message: String) : ProfileEvent()

}