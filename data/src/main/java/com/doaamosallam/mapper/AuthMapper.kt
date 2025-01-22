package com.doaamosallam.mapper

import com.doaamosallam.domain.models.auth.Login
import com.doaamosallam.domain.models.auth.Register
import com.doaamosallam.local.loginData.LoginEntity
import com.doaamosallam.local.registerData.RegisterEntity
import kotlinx.coroutines.flow.Flow

fun RegisterEntity.toDomain(): Register {
    return Register(
        name = name,
        phone = phone,
        email = email,
        password = password,
        confirmPassword = confirmPassword
    )
}

fun Register.toEntity(): RegisterEntity {
    return RegisterEntity(
        name = name,
        phone = phone,
        email = email,
        password = password,
        confirmPassword = confirmPassword
    )
}

fun LoginEntity.toDomain(): Login {
    return Login(
        email = this.email,
        password = this.password
    )
}

