package com.doaamosallam.local.registerData

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.doaamosallam.domain.models.auth.Register

@Entity(tableName = "Register_table")
data class RegisterEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Long? = null,
    var name:String,
    var phone:String,
    var email:String,
    var password:String,
    var confirmPassword:String
)
