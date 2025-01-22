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

    @Query("SELECT * FROM register_table WHERE name = :name LIMIT 1")
    suspend fun getUserByName(name: String):RegisterEntity?


    // Fetch user by email
    @Query("SELECT * FROM Register_table WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): RegisterEntity?


    @Query("SELECT * FROM register_table WHERE phone = :phone")
    suspend fun getUserByPhone(phone: String): RegisterEntity?

    @Query("SELECT * FROM register_table WHERE password = :password")
    suspend fun getUserByPassword(password: String): RegisterEntity?

    @Query("UPDATE register_table SET password = :newPassword WHERE email = :email")
    suspend fun updatePassword(email: String, newPassword: String)


}