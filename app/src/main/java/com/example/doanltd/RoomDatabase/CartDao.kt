package com.example.doanltd

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
<<<<<<< HEAD
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
=======
import com.example.doanltd.RoomDatabase.CartRoom.CartItemEntity
>>>>>>> d7dd8f80d2a134ac95f41b9bb40b3f168decfc9a

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_items WHERE MaSp=:MaSp")
    suspend fun getCartItemById(MaSp: String): CartItemEntity?

    @Query("SELECT * FROM cart_items")
    suspend fun getAllCartItems(): List<CartItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartItemEntity)

    @Update
    suspend fun updateCartItem(cartItem: CartItemEntity)

    @Delete
    suspend fun deleteCartItem(cartItem: CartItemEntity)


}

