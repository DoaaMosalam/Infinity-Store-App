package com.doaamosallam.infinitystore.di

import android.app.Application
import androidx.room.Room
import com.doaamosallam.data.local.AppDatabase
import com.doaamosallam.data.local.CartData.CartDAO
import com.doaamosallam.data.local.LoginData.LoginDAO
import com.doaamosallam.data.local.RegisterData.RegisterDAO
import com.doaamosallam.infinitystore.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    fun provideCartDao(db: AppDatabase):CartDAO = db.cartDao()

}