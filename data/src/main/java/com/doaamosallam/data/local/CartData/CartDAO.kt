package com.doaamosallam.data.local.CartData

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CartDAO {


    // save(insert) product in cart table
   @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProductToCart(cartProduct: CartProductEntity)

//    // get all cart from table
//    @Query("SELECT * FROM cart_table")
//    fun getAllCart(): List<CartEntity>

    // get product from cart table
@Query("SELECT * FROM cart_product_table ORDER BY id DESC")
    suspend fun getProductFromCart(): List<CartProductEntity>
//            Flow<List<CartEntity>>


}
