package com.doaamosallam.data.repo

import com.doaamosallam.data.local.RegisterDAO
import com.doaamosallam.data.local.RegisterEntity
import com.doaamosallam.domain.models.Register
import com.doaamosallam.domain.repo.RegisterRepo

class RegisterRepoImp(private val registerDAO: RegisterDAO):RegisterRepo {
    override suspend fun registerUser(register: Register) {
        val registerEntity = RegisterEntity(
            name = register.name,
            phone = register.phone,
            email = register.email,
            password = register.password,
            confirmPassword = register.confirmPassword
        )
        registerDAO.insertRegister(registerEntity)
    }
}