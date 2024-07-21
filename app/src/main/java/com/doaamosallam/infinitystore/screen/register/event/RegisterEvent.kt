package com.doaamosallam.infinitystore.screen.register.event

sealed class RegisterEvent {
    data class Register(
        var name: String,
        var phone: String,
        var email: String,
        var password: String,
        var confirmPassword: String
    ) : RegisterEvent()
}