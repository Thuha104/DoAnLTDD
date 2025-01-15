//package com.example.doanltd
//
//import android.content.Context
//
//class CartManager(context: Context) {
//    private val cartDao = AppDatabase.getDatabase(context).cartDao()
//
//    suspend fun getCartItems(): List<CartItem> {
//        return cartDao.getAllCartItems().map { it.toCartItem() }
//    }
//
////    suspend fun addCartItem(cartItem: CartItem) {
////        cartDao.insertCartItem(cartItem.toEntity())
////    }
//suspend fun insertCartItem(cartItem: CartItem) {
//    val existingItem = cartDao.getCartItemById(cartItem.id) // Kiểm tra sản phẩm có tồn tại không
//    if (existingItem != null) {
//        // Nếu đã tồn tại, tăng số lượng
//        val updatedQuantity = existingItem.quantity + cartItem.quantity
//        cartDao.updateCartItem(existingItem.copy(quantity = updatedQuantity))
//    } else {
//        // Nếu chưa tồn tại, thêm mới
//        cartDao.insertCartItem(cartItem.toEntity())
//    }
//}
//
//
//}


package com.example.doanltd

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartManager(context: Context) {
    private val cartDao = AppDatabase.getDatabase(context).cartDao()

    suspend fun getCartItems(): List<CartItem> = withContext(Dispatchers.IO) {
        cartDao.getAllCartItems().map { it.toCartItem() }
    }

    suspend fun addToCart(cartItem: CartItem) = withContext(Dispatchers.IO) {
        val existingItem = cartDao.getCartItemById(cartItem.id)
        if (existingItem != null) {
            // If the item exists, increase the quantity
            val updatedQuantity = existingItem.quantity + cartItem.quantity
            cartDao.updateCartItem(existingItem.copy(quantity = updatedQuantity))
        } else {
            // If the item doesn't exist, insert it as a new item
            cartDao.insertCartItem(cartItem.toEntity())
        }
    }
}

