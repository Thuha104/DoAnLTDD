package com.example.doanltd

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

//@Dao
//interface CartDao {
//
//    @Query("SELECT * FROM cart_items")
//    suspend fun getAllCartItems(): List<CartItem>
//
//    @Insert
//    suspend fun insertCartItem(cartItem: CartItem)
//
//    @Update
//    suspend fun updateCartItem(cartItem: CartItem)
//
//    @Delete
//    suspend fun deleteCartItem(cartItem: CartItem)
//
//
//}

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_items WHERE id = :id LIMIT 1")
    suspend fun getCartItemById(id: Int): CartItemEntity?

    @Query("SELECT * FROM cart_items")
    suspend fun getAllCartItems(): List<CartItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartItemEntity)

    @Update
    suspend fun updateCartItem(cartItem: CartItemEntity)

    @Delete
    suspend fun deleteCartItem(cartItem: CartItemEntity)


}

