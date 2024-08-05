package com.doaamosallam.local.registerData

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RegisterDAO {
    //save info User after register in data
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRegister(registerEntity: RegisterEntity): Long

    // get info User after register in data
    @Query("SELECT * FROM register_table Limit 1")
    suspend fun getUser(): RegisterEntity

}