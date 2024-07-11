package com.doaamosallam.infinitystore.di

import com.doaamosallam.data.local.LoginDAO
import com.doaamosallam.data.local.RegisterDAO
import com.doaamosallam.data.repo.LoginRepoImp
import com.doaamosallam.data.repo.RegisterRepoImp
import com.doaamosallam.domain.repo.LoginRepo
import com.doaamosallam.domain.repo.RegisterRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideLoginRepo(
        loginDAO: LoginDAO
    ): LoginRepo = LoginRepoImp(loginDAO)

    @Provides
    @Singleton
    fun provideRegisterRepo(
        registerDAO: RegisterDAO,
        loginDAO: LoginDAO
    ): RegisterRepo = RegisterRepoImp(
        registerDAO,
        loginDAO
    )
}