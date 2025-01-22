package com.doaamosallam.repository

import com.doaamosallam.local.loginData.LoginDAO
import com.doaamosallam.local.loginData.LoginEntity
import com.doaamosallam.local.registerData.RegisterDAO
import com.doaamosallam.domain.models.auth.Register
import com.doaamosallam.domain.repo.RegisterRepo
import com.doaamosallam.mapper.toDomain
import com.doaamosallam.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RegisterRepoImp(
    private val registerDAO: RegisterDAO,
    private val loginDAO: LoginDAO
) : RegisterRepo {
    override suspend fun registerUser(register: Register) {
        // Convert Register to RegisterEntity
        val registerEntity = register.toEntity()
        val loginEntity = LoginEntity(
            email = register.email,
            password = register.password
        )
        // Insert into both tables
        registerDAO.insertRegister(registerEntity)
        loginDAO.insertLogin(loginEntity)
    }
    override suspend fun getUserByEmail(email: String): Register {
        return registerDAO.getUserByEmail(email)!!.toDomain()
    }

    override suspend fun getUserByName(name: String): Register{
        return registerDAO.getUserByName(name)!!.toDomain()
    }


    override suspend fun getUserByPhone(phone: String): Register? {
        return registerDAO.getUserByPhone(phone)?.toDomain()
    }

    override suspend fun getUserByPassword(password: String): Register? {
        return registerDAO.getUserByPassword(password)?.toDomain()
    }

    override suspend fun updatePassword(email: String, newPassword: String) {
        registerDAO.updatePassword(email, newPassword)
    }
}