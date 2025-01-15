package com.example.doanltd

import androidx.room.Entity
import androidx.room.PrimaryKey

import androidx.room.*

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val price: Double,
    val quantity: Int,
    val imageUrl: String
){
    fun toCartItem() = CartItem(id, name, price, quantity,imageUrl)
}




