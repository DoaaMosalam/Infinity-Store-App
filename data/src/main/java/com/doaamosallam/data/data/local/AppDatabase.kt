package com.doaamosallam.data.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LoginEntity::class, RegisterEntity::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun loginDao(): LoginDAO
    abstract fun registerDao(): RegisterDAO

}