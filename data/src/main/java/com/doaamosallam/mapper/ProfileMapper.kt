package com.doaamosallam.mapper

import com.doaamosallam.domain.models.profile.ImagesUser
import com.doaamosallam.local.profileDao.ImageUserEntity

fun ImageUserEntity.toDomain(): ImagesUser {
    return ImagesUser(
        id = this.id,
        imageUri = this.imageUri
    )
}

fun List<ImagesUser>.toEntity(): List<ImageUserEntity> {
    return this.map { imageUser ->
        ImageUserEntity(
            id = imageUser.id,
            imageUri = imageUser.imageUri
        )

    }
}