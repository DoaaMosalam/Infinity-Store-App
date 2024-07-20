package com.doaamosallam.infinitystore.di

import com.doaamosallam.data.local.CartData.CartDAO
import com.doaamosallam.data.local.LoginData.LoginDAO
import com.doaamosallam.data.local.RegisterData.RegisterDAO
import com.doaamosallam.domain.repo.CartRepo
import com.doaamosallam.domain.repo.LoginRepo
import com.doaamosallam.domain.repo.RegisterRepo
import com.doaamosallam.repo.CartRepoImp
import com.doaamosallam.repo.LoginRepoImp
import com.doaamosallam.repo.RegisterRepoImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoRoomDataModule {
    // login user repo room database
    @Provides
    @Singleton
    fun provideLoginRepo(
        loginDAO: LoginDAO
    ): LoginRepo = LoginRepoImp(loginDAO)
    // register user repo room database
    @Provides
    @Singleton
    fun provideRegisterRepo(
        registerDAO: RegisterDAO,
        loginDAO: LoginDAO
    ): RegisterRepo = RegisterRepoImp(
        registerDAO,
        loginDAO
    )
    // provide cart product repo room database
    @Provides
    @Singleton
    fun provideCartRepo(cartDAO: CartDAO):CartRepo = CartRepoImp(cartDAO)
}
