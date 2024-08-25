package com.doaamosallam.repository

import com.doaamosallam.domain.models.profile.ImagesUser
import com.doaamosallam.domain.repo.ProfileRepository
import com.doaamosallam.local.profileDao.ProfileImageDAO
import com.doaamosallam.mapper.toDomain

import com.doaamosallam.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProfileRepoImp(
   private val profileDAO: ProfileImageDAO
): ProfileRepository {

    override suspend fun addImageToProfile(imageUri: List<ImagesUser>) {
        profileDAO.insertImage(imageUri.toEntity())
    }

    override fun getImageFromProfile(): Flow<ImagesUser?> {
        return profileDAO.getImage().map { entity ->
            entity?.toDomain()

        }


    }

}
