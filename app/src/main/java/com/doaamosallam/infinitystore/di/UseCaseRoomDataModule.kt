package com.doaamosallam.infinitystore.di

import com.doaamosallam.domain.repo.CartRepository
import com.doaamosallam.domain.repo.LoginRepo
import com.doaamosallam.domain.repo.RegisterRepo
import com.doaamosallam.domain.usecase.CartUseCase
import com.doaamosallam.domain.usecase.LoginUseCase
import com.doaamosallam.domain.usecase.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object UseCaseRoomDataModule {
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

    @Provides
    @Singleton
    fun provideCartUseCase(cartRepo: CartRepository): CartUseCase {
        return CartUseCase(cartRepo)
    }
}