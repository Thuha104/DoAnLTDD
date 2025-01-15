package com.example.doanltd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CartDao {

    @Query("SELECT * FROM cart_items")
    suspend fun getAllCartItems(): List<CartItemEntity>

    @Insert
    suspend fun insertCartItem(cartItem: CartItemEntity)
}
