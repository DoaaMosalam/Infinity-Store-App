package com.doaamosallam.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.doaamosallam.local.cartData.CartDAO
import com.doaamosallam.local.cartData.CartProductEntity
import com.doaamosallam.local.loginData.LoginDAO
import com.doaamosallam.local.loginData.LoginEntity
import com.doaamosallam.local.registerData.RegisterDAO
import com.doaamosallam.local.registerData.RegisterEntity

/**
 * The Room database for this app.
 */
@Database(
    entities = [
        LoginEntity::class,
        RegisterEntity::class,
        CartProductEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun loginDao(): LoginDAO

    abstract fun registerDao(): RegisterDAO

    abstract fun cartDao(): CartDAO
}