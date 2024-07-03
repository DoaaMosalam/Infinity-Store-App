package com.doaamosallam.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LoginEntity::class, RegisterEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase:RoomDatabase() {
    abstract fun loginDao(): LoginDAO
    abstract fun registerDao(): RegisterDAO
}