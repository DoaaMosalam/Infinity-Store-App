package com.doaamosallam.data.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LoginDAO {
    //save info User after Login in data.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLogin(loginEntity: LoginEntity):Long

    //return user in login data
    @Query("SELECT * FROM login_table WHERE email = :email AND password = :password")
    suspend fun getLogin(email: String, password: String): LoginEntity?
}