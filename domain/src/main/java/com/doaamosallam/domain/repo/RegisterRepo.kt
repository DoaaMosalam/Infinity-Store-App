package com.doaamosallam.domain.repo

import com.doaamosallam.domain.models.auth.Register
import kotlinx.coroutines.flow.Flow

interface RegisterRepo {
    suspend fun registerUser(register: Register)

    suspend fun getUserByName(name: String): Register?

    suspend fun getUserByEmail(email: String):Register?

    suspend fun getUserByPhone(phone: String):Register?


    suspend fun getUserByPassword(password: String):Register?

    suspend fun updatePassword(email: String, newPassword: String)
}