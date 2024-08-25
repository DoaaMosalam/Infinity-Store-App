package com.doaamosallam.local.profileDao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_images")
data class ImageUserEntity(
    @PrimaryKey
    val id: Int,
    val imageUri: String
)
