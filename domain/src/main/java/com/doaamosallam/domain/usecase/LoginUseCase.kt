package com.doaamosallam.domain.usecase

import com.doaamosallam.domain.models.Login
import com.doaamosallam.domain.repo.LoginRepo

class LoginUseCase(private val loginRepo: LoginRepo) {
    suspend fun LoginUser(login: Login) = loginRepo.loginUser(login)
}