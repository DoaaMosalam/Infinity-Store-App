package com.doaamosallam.infinitystore.di

import com.doaamosallam.remote.APIService
import com.doaamosallam.domain.repo.CategoriesRepo
import com.doaamosallam.domain.repo.CategoryListRepo
import com.doaamosallam.domain.repo.ProductSearchRepo
import com.doaamosallam.domain.repo.ProductsRepo
import com.doaamosallam.infinitystore.data.FirebaseAuthRepository
import com.doaamosallam.infinitystore.data.FirebaseAuthRepositoryImpl
import com.doaamosallam.repository.CategoriesRepoImp
import com.doaamosallam.repository.CategoryListRepoImp
import com.doaamosallam.repository.ProductSearchRepoImp
import com.doaamosallam.repository.ProductsRepoImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    // app module firebase
    @Provides
    @Singleton
    fun provideFirebaseAuthRepository(): FirebaseAuthRepository = FirebaseAuthRepositoryImpl()
    // provide product repo

    @Provides
    @Singleton
    fun provideProductRepo(apiService: APIService): ProductsRepo = ProductsRepoImp(apiService)
    // provide category repo
    @Provides
    @Singleton
    fun provideCategoryListRepo(apiService: APIService): CategoryListRepo {
        return CategoryListRepoImp(apiService)
    }
    // provide search products
    @Provides
    @Singleton
    fun provideSearchProductRepo(apiService: APIService):ProductSearchRepo = ProductSearchRepoImp(apiService)

    //provide categories repo
    @Provides
    @Singleton
    fun provideCategoriesRepo(apiService: APIService):CategoriesRepo = CategoriesRepoImp(apiService)
}