package com.doaamosallam.infinitystore.di

import android.app.Application
import androidx.room.Room
import com.doaamosallam.data.data.local.AppDatabase
import com.doaamosallam.data.data.local.LoginDAO
import com.doaamosallam.data.data.local.RegisterDAO
import com.doaamosallam.infinitystore.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModuleRoomData {

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

}