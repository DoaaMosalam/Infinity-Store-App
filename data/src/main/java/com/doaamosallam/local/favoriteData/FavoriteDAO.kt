package com.doaamosallam.local.favoriteData

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProductToFavorite(favoriteProduct: FavoriteEntity)

    @Query("SELECT * FROM favorite_product_table ORDER BY id DESC")
    fun getProductFromFavorite(): Flow<List<FavoriteEntity>>

    @Delete
    suspend fun deleteProductFromFavorite(favoriteProduct: FavoriteEntity)
}

