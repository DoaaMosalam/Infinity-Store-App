package com.doaamosallam.infinitystore.di

import com.doaamosallam.domain.repo.CategoryListRepo
import com.doaamosallam.domain.repo.ProductSearchRepo
import com.doaamosallam.domain.repo.ProductsRepo
import com.doaamosallam.domain.usecase.CategoryListUseCase
import com.doaamosallam.domain.usecase.ProductSearchUseCase
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
    fun provideProductUseCase(productsRepo: ProductsRepo): ProductsUseCase = ProductsUseCase(productsRepo)

    // provide category list use case
    @Provides
    @Singleton
    fun provideCategoryListUseCase(categoryListRepo: CategoryListRepo): CategoryListUseCase = CategoryListUseCase(categoryListRepo)

    // provide search product use case
    @Provides
    @Singleton
    fun provideProductSearchUseCase(productSearchRepo: ProductSearchRepo): ProductSearchUseCase = ProductSearchUseCase(productSearchRepo)
}