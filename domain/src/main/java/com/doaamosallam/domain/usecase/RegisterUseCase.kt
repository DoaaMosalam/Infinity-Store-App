package com.doaamosallam.domain.usecase

import com.doaamosallam.domain.models.auth.Register
import com.doaamosallam.domain.repo.RegisterRepo
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val registerRepo: RegisterRepo) {
    suspend fun registerUser(register: Register) = registerRepo.registerUser(register)

    suspend fun getUserByName(name:String) = registerRepo.getUserByName(name)

    suspend fun getUserByEmail(email: String) = registerRepo.getUserByEmail(email)

    suspend fun getUserByPhone(phone: String) = registerRepo.getUserByName(phone)

    suspend fun getUserByPassword(password: String) = registerRepo.getUserByPassword(password)

    suspend fun updatePassword(email: String, newPassword: String) = registerRepo.updatePassword(email, newPassword)
}