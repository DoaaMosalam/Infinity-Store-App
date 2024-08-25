package com.doaamosallam.local.profileDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileImageDAO {
    // save image in profile table
    @Insert(onConflict = OnConflictStrategy.REPLACE) // Use REPLACE to update the image if it already exists
    suspend fun insertImage(imageEntity: List<ImageUserEntity>)

    //return image from profile table
  @Query("SELECT * FROM profile_images LIMIT 1")
     fun getImage(): Flow<ImageUserEntity?>


}