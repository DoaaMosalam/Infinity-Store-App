package com.doaamosallam.infinitystore.viewmodel.forget_password

sealed class ForgetPasswordIntent {
    data class ForgetPassword(
        val email: String
    ) : ForgetPasswordIntent()
}