package com.doaamosallam.domain.repo

import com.doaamosallam.domain.models.Register

interface RegisterRepo {
    suspend fun registerUser(register: Register)

}