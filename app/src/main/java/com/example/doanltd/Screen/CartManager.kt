package com.example.doanltd

import android.content.Context

class CartManager(context: Context) {
    private val cartDao = AppDatabase.getDatabase(context).cartDao()

    suspend fun getCartItems(): List<CartItem> {
        return cartDao.getAllCartItems().map { it.toCartItem() }
    }

    suspend fun addCartItem(cartItem: CartItem) {
        cartDao.insertCartItem(cartItem.toEntity())
    }
}
