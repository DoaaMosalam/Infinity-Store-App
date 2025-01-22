package com.doaamosallam.infinitystore.screen.profile.state

import com.doaamosallam.domain.models.profile.ImagesUser

data class ProfileUiState(
    val images: ImagesUser = ImagesUser(0, ""),
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val password: String = "",

    val loading: Boolean = false,
    val success: Boolean = false,
    val error: String = ""
)
