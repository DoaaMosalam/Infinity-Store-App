package com.doaamosallam.infinitystore.data

import com.doaamosallam.infinitystore.common.Resource
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthRepository {
    suspend fun loginWithGoogle(
        idToken: String
    ): Flow<Resource<String>>

    suspend fun loginWithFacebook(
        token: String
    ): Flow<Resource<String>>

    suspend fun sendUpdatePasswordEmail(email: String): Flow<Resource<String>>

}