package com.doaamosallam.data.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Login_table")
data class LoginEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long? =null,
    val email:String,
    val password:String

)
