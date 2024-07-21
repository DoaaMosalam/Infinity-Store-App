package com.doaamosallam.infinitystore.screen.login.event

import com.doaamosallam.domain.models.auth.Login

sealed class LoginEvent{
    data class Login(
        val email: String,
        val password: String
    ) : LoginEvent()
}