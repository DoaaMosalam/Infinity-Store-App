package com.doaamosallam.infinitystore.screen.profile.state

import com.doaamosallam.domain.models.profile.ImagesUser

data class ProfileUiState(
    val images: ImagesUser = ImagesUser(0, ""),
    val loading: Boolean = false,
    val success: Boolean = false,
    val error: String = ""
)
