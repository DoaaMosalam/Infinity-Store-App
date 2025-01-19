package com.doaamosallam.domain.usecase

import com.doaamosallam.domain.models.auth.Login
import com.doaamosallam.domain.repo.LoginRepo
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepo: LoginRepo) {
    suspend fun loginUser(login: Login) = loginRepo.loginUser(login)

}