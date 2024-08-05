package com.doaamosallam.domain.repo

import com.doaamosallam.domain.models.auth.Register

interface RegisterRepo {
    suspend fun registerUser(register: Register)
    suspend fun getUser(): Register
}