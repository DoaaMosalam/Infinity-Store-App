package com.doaamosallam.infinitystore.viewmodel.Login

sealed class LoginIntent {
    data class Login(val email:String,val password:String):LoginIntent()
}