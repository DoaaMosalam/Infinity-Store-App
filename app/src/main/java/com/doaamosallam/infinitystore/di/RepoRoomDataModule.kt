package com.doaamosallam.infinitystore.di

import com.doaamosallam.domain.repo.CartRepository
import com.doaamosallam.domain.repo.FavoriteRepository
import com.doaamosallam.domain.repo.LoginRepo
import com.doaamosallam.domain.repo.ProfileRepository
import com.doaamosallam.domain.repo.RegisterRepo
import com.doaamosallam.local.cartData.CartDAO
import com.doaamosallam.local.favoriteData.FavoriteDAO
import com.doaamosallam.local.loginData.LoginDAO
import com.doaamosallam.local.profileDao.ProfileImageDAO
import com.doaamosallam.local.registerData.RegisterDAO
import com.doaamosallam.repository.CartRepoImp
import com.doaamosallam.repository.FavoriteRepoImp
import com.doaamosallam.repository.LoginRepoImp
import com.doaamosallam.repository.ProfileRepoImp
import com.doaamosallam.repository.RegisterRepoImp
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
    fun provideCartRepo(cartDAO: CartDAO): CartRepository = CartRepoImp(cartDAO)

    @Provides
    @Singleton
    fun provideFavoriteRepo(favoriteDAO: FavoriteDAO): FavoriteRepository =
        FavoriteRepoImp(favoriteDAO)

    @Provides
    @Singleton
    fun provideProfileRepo(profileImageDAO: ProfileImageDAO): ProfileRepository =
        ProfileRepoImp(profileImageDAO)


}
