package com.doaamosallam.infinitystore.screen.setting.state

import com.doaamosallam.domain.models.profile.ImagesUser

data class SettingUiState(
    val loading: Boolean = false,
    val success: Boolean = false,
    val error: String = ""
)
