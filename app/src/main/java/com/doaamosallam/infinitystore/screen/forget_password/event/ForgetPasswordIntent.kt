package com.doaamosallam.infinitystore.screen.forget_password.event

sealed class ForgetPasswordIntent {
    data class ForgetPassword(
        val email: String
    ) : ForgetPasswordIntent()
}