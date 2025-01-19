package com.doaamosallam.infinitystore.screen.login.state

import com.doaamosallam.domain.models.auth.Login

data class LoginUiState(
    val login: Login = Login("", ""),
    val email: String = "",
    val password: String = "",
    val loading: Boolean = false,
    val success: Boolean = false,
    val error: String = ""
)