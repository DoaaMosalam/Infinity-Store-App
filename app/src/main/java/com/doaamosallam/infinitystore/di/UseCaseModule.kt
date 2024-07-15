package com.doaamosallam.infinitystore.di

import com.doaamosallam.domain.repo.ProductsRepo
import com.doaamosallam.domain.usecase.ProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    // provide product use case
    @Provides
    @Singleton
    fun provideProductUseCase(productsRepo: ProductsRepo):ProductsUseCase {
       return ProductsUseCase(productsRepo)
    }

}