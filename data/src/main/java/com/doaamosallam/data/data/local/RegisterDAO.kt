package com.doaamosallam.data.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface RegisterDAO {
    //save info User after register in data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRegister(registerEntity: RegisterEntity): Long
}