package com.doaamosallam.repository

import com.doaamosallam.local.loginData.LoginDAO
import com.doaamosallam.local.loginData.LoginEntity
import com.doaamosallam.local.registerData.RegisterDAO
import com.doaamosallam.local.registerData.RegisterEntity
import com.doaamosallam.domain.models.auth.Register
import com.doaamosallam.domain.repo.RegisterRepo

class RegisterRepoImp(
    private val registerDAO: RegisterDAO,
    private val loginDAO: LoginDAO
) : RegisterRepo {
    override suspend fun registerUser(register: Register) {
        val registerEntity = RegisterEntity(
            name = register.name,
            phone = register.phone,
            email = register.email,
            password = register.password,
            confirmPassword = register.confirmPassword
        )
        val loginEntity = LoginEntity(
            email = register.email,
            password = register.password
        )
        // Insert into both tables
        registerDAO.insertRegister(registerEntity)
        loginDAO.insertLogin(loginEntity)
    }
/*
* get information user name, email, password from table register.
* */
    override suspend fun getUser(): Register {
        val registerEntity = registerDAO.getUser()
        return Register(
            name = registerEntity.name,
            phone = registerEntity.phone,
            email = registerEntity.email,
            password = registerEntity.password,
            confirmPassword = registerEntity.confirmPassword
        )
    }
}