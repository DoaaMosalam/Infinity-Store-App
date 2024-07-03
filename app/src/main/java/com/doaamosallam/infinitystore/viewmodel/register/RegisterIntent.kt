package com.doaamosallam.infinitystore.viewmodel.register

sealed class RegisterIntent {
    data class Register(
        var name:String,
        var phone:String,
        var email:String,
        var password:String,
        var confirmPassword:String
    ):RegisterIntent()
}