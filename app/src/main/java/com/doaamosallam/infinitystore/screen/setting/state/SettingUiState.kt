package com.doaamosallam.infinitystore.screen.setting.state

data class SettingUiState(
    val loading: Boolean = false,
    val success: Boolean = false,
    val error: String = ""
)
