package com.doaamosallam.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.doaamosallam.data.local.CartData.CartDAO
import com.doaamosallam.data.local.CartData.CartProductEntity
import com.doaamosallam.data.local.LoginData.LoginDAO
import com.doaamosallam.data.local.LoginData.LoginEntity
import com.doaamosallam.data.local.RegisterData.RegisterDAO
import com.doaamosallam.data.local.RegisterData.RegisterEntity

@Database(
    entities = [LoginEntity::class, RegisterEntity::class, CartProductEntity::class],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun loginDao(): LoginDAO
    abstract fun registerDao(): RegisterDAO
    abstract fun cartDao(): CartDAO
}