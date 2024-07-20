package com.doaamosallam.repo

import com.doaamosallam.data.local.LoginData.LoginDAO
import com.doaamosallam.domain.models.auth.Login
import com.doaamosallam.domain.repo.LoginRepo

class LoginRepoImp(private val loginDAO: LoginDAO):LoginRepo {
    override suspend fun loginUser(
        login: Login
    ): Login? {
        val loginEntity = loginDAO.getLogin(login.email, login.password)
        return loginEntity?.let {
            Login(
                email = it.email,
                password = it.password
            )
        }
    }
}