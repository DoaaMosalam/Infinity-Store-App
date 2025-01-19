package com.doaamosallam.domain.usecase

import com.doaamosallam.domain.models.profile.ImagesUser
import com.doaamosallam.domain.repo.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend fun addImageToProfile(imageUri: List<ImagesUser>) = profileRepository.addImageToProfile(imageUri)

    fun getImageFromProfile(): Flow<ImagesUser?> = profileRepository.getImageFromProfile()

}