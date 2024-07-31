package com.doaamosallam.infinitystore.screen.login.navigation

import com.doaamosallam.domain.models.auth.Login

sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    data class Success(val login: Login) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
    data class Content(val email: String = "", val password: String = "") : LoginUiState()
}


