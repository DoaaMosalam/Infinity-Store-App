package com.doaamosallam.domain.usecase

import com.doaamosallam.domain.models.auth.Register
import com.doaamosallam.domain.repo.RegisterRepo
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val registerRepo: RegisterRepo) {
    suspend fun RegisterUser(register: Register) = registerRepo.registerUser(register)

    suspend fun getUser() = registerRepo.getUser()
}