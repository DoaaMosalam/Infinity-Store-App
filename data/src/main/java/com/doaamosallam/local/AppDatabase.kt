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
import com.doaamosallam.local.registerData.RegisterDAO
import com.doaamosallam.local.registerData.RegisterEntity
//val MIGRATION_2_3 = object : Migration(2, 3) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        // Perform schema changes here
//        // Example: database.execSQL("ALTER TABLE favorite_product_table ADD COLUMN new_column_name TEXT")
//    }
//}
/**
 * The Room database for this app.
 */
@Database(
    entities = [
        LoginEntity::class,
        RegisterEntity::class,
        CartProductEntity::class,
        FavoriteEntity::class
    ],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun loginDao(): LoginDAO

    abstract fun registerDao(): RegisterDAO

    abstract fun cartDao(): CartDAO

    abstract fun favoriteDao(): FavoriteDAO
    companion object {
        // Register the migration in your database builder
        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Schema changes
            }
        }
    }

}

