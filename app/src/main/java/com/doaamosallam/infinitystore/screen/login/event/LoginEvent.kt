package com.doaamosallam.infinitystore.screen.login.event

sealed class LoginEvent {
    data class Login(
        val email: String,
        val password: String
    ) : LoginEvent()
}