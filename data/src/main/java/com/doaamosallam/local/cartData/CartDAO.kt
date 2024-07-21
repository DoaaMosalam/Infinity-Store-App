package com.doaamosallam.local.cartData

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.doaamosallam.domain.models.cart.CartProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProductToCart(cartProduct: CartProductEntity)

    @Query("SELECT * FROM cart_product_table ORDER BY id DESC")
    fun getProductFromCart(): Flow<List<CartProductEntity>>

    @Delete
    suspend fun deleteProductFromCart(cartProduct: CartProductEntity)

}
