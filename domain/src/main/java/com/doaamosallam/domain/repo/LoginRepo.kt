package com.doaamosallam.domain.repo

import com.doaamosallam.domain.models.Login

interface LoginRepo {
    suspend fun loginUser(login: Login):Login?
}