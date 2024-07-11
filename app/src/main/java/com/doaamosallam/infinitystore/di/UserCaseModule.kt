package com.doaamosallam.infinitystore.di

import com.doaamosallam.domain.repo.LoginRepo
import com.doaamosallam.domain.repo.RegisterRepo
import com.doaamosallam.domain.usecase.LoginUseCase
import com.doaamosallam.domain.usecase.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object UserCaseModule {
    @Provides
    @Singleton
    fun provideLoginUseCase(loginRepo: LoginRepo): LoginUseCase {
        return LoginUseCase(loginRepo)
    }

    @Provides
    @Singleton
    fun provideRegisterUseCase(registerRepo: RegisterRepo): RegisterUseCase {
        return RegisterUseCase(registerRepo)
    }
}