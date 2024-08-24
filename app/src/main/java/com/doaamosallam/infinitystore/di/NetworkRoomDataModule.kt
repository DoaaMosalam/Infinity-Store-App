package com.doaamosallam.infinitystore.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.doaamosallam.infinitystore.util.Constant
import com.doaamosallam.local.AppDatabase
import com.doaamosallam.local.cartData.CartDAO
import com.doaamosallam.local.favoriteData.FavoriteDAO
import com.doaamosallam.local.loginData.LoginDAO
import com.doaamosallam.local.registerData.RegisterDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkRoomDataModule {

    @Provides
    @Singleton
    fun provideAppDatabaseBuilder(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            Constant.DATABASE_NAME
        )
            .fallbackToDestructiveMigration() // This will destroy and recreate the database
            .addMigrations(AppDatabase.MIGRATION_2_3)
            .build()

    }


    @Provides
    @Singleton
    fun provideLoginDao(db: AppDatabase): LoginDAO = db.loginDao()

    @Provides
    @Singleton
    fun provideRegisterDao(db: AppDatabase): RegisterDAO = db.registerDao()

    @Provides
    @Singleton
    fun provideCartDao(db: AppDatabase): CartDAO = db.cartDao()

    @Provides
    @Singleton
    fun providerFavoriteDao(db: AppDatabase): FavoriteDAO = db.favoriteDao()

}