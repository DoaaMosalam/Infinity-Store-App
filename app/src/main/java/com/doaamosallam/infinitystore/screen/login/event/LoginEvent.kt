package com.doaamosallam.infinitystore.screen.login.event

sealed class LoginEvent {
    data class Login(val login: com.doaamosallam.domain.models.auth.Login) : LoginEvent()
    data class UpdateEmail(val email: String) : LoginEvent()
    data class UpdatePassword(val password: String) : LoginEvent()
    data class SetLoading(val isLoading: Boolean) : LoginEvent()
    data class SetError(val message: String) : LoginEvent()
    data class SetSuccess(val message: String) : LoginEvent()

}