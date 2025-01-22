package com.doaamosallam.repository

import com.doaamosallam.local.loginData.LoginDAO
import com.doaamosallam.domain.models.auth.Login
import com.doaamosallam.domain.repo.LoginRepo
import com.doaamosallam.mapper.toDomain

class LoginRepoImp(private val loginDAO: LoginDAO):LoginRepo {
    override suspend fun loginUser(
        login: Login
    ): Login? {
        val loginEntity = loginDAO.getLogin(login.email, login.password)
        return loginEntity?.toDomain()
//        return loginEntity?.let {
//            Login(
//                email = it.email,
//                password = it.password
//            )
//        }
    }
}