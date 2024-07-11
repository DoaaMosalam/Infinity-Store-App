package com.doaamosallam.infinitystore.viewmodel.Login

import com.doaamosallam.domain.models.Login

sealed class LoginViewState {
    object Idle : LoginViewState()
    object Loading : LoginViewState()
    data class Success(val login: Login) : LoginViewState()
    data class Error(val message: String) : LoginViewState()
    data class Content(val email: String = "", val password: String = "") : LoginViewState()
}