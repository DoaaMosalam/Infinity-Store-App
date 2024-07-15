package com.doaamosallam.domain.models.auth

data class Register(
    var name:String,
    var phone:String,
    var email:String,
    var password:String,
    var confirmPassword:String
)

