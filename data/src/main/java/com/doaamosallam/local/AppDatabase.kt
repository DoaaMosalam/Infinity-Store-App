package com.doaamosallam.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.doaamosallam.local.cartData.CartDAO
import com.doaamosallam.local.cartData.CartProductEntity
import com.doaamosallam.local.favoriteData.FavoriteDAO
import com.doaamosallam.local.favoriteData.FavoriteEntity
import com.doaamosallam.local.loginData.LoginDAO
import com.doaamosallam.local.loginData.LoginEntity
import com.doaamosallam.local.profileDao.ProfileImageDAO
import com.doaamosallam.local.profileDao.ImageUserEntity
import com.doaamosallam.local.registerData.RegisterDAO
import com.doaamosallam.local.registerData.RegisterEntity

/**
 * The Room database for this app.
 */
@Database(
    entities = [
        LoginEntity::class,
        RegisterEntity::class,
        CartProductEntity::class,
        FavoriteEntity::class,
        ImageUserEntity::class
    ],
    version = 5,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun loginDao(): LoginDAO

    abstract fun registerDao(): RegisterDAO

    abstract fun cartDao(): CartDAO

    abstract fun favoriteDao(): FavoriteDAO

    abstract fun profileDao(): ProfileImageDAO

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Schema changes from version 1 to 2
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create image_user table
                database.execSQL(
                    "CREATE TABLE image_user (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                            "imageUri TEXT NOT NULL)"
                )
            }
        }

        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Example of adding a column
                database.execSQL("ALTER TABLE image_user ADD COLUMN new_column_name TEXT")
            }
        }

        val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Handle any schema changes for version 5
            }
        }
    }
}

