package com.doaamosallam.domain.repo

import com.doaamosallam.domain.models.auth.Register
import com.doaamosallam.domain.models.profile.ImagesUser
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    // save image in profile table
    suspend fun addImageToProfile(imageUri: List<ImagesUser>)

    //return image from profile table
    fun getImageFromProfile(): Flow<ImagesUser?>



}