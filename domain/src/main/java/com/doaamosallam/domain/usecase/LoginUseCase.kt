package com.doaamosallam.domain.usecase

import com.doaamosallam.domain.models.Login
import com.doaamosallam.domain.repo.LoginRepo
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepo: LoginRepo) {
    suspend fun LoginUser(login: Login) = loginRepo.loginUser(login)
}