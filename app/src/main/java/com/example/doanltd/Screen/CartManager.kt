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


//package com.example.doanltd
//
//import android.content.Context
//import android.util.Log
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//
//class CartManager(context: Context) {
//    private val cartDao = AppDatabase.getDatabase(context).cartDao()
//
//    suspend fun getCartItems(): List<CartItemEntity> = withContext(Dispatchers.IO) {
//     //   cartDao.getAllCartItems().map { it.toCartItem() }
//    }
//
//    suspend fun addToCart(cartItem: CartItem) = withContext(Dispatchers.IO) {
//        val existingItem = cartDao.getCartItemById(cartItem.id)
//      Log.d(String.toString(),"${cartItem}")
//        if (existingItem != null) {
//            // If the item exists, increase the quantity
//            val updatedQuantity = existingItem.quantity + cartItem.quantity
//            cartDao.updateCartItem(existingItem.copy(quantity = updatedQuantity))
//        } else {
//            // If the item doesn't exist, insert it as a new item
//            cartDao.insertCartItem(cartItem.toEntity())
//        }
//    }
//}
package com.example.doanltd

import android.content.Context
import android.util.Log
import com.example.doanltd.RoomDatabase.CartRoom.CartItemEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartManager(context: Context) {
    private val cartDao = AppDatabase.getDatabase(context).cartDao()

    // Get all cart items from the database
    suspend fun getCartItems(): List<CartItemEntity> = withContext(Dispatchers.IO) {
        cartDao.getAllCartItems()
    }

    // Add a new item to the cart or update an existing one
    // số lương sp
    suspend fun addToCart(cartItem: CartItemEntity) = withContext(Dispatchers.IO) {
        val existingItem = cartDao.getCartItemById(cartItem.MaSp)
        if (existingItem != null)
<<<<<<< HEAD
=======
<<<<<<< HEAD
        {//kiem tra so luong của sản phẩm >= so sl sp đó trong giỏ hàng
            val updatedQuantity = existingItem.quantity + cartItem.quantity

            if (updatedQuantity<=cartItem.SoLuongSP){
=======
>>>>>>> 8d37676d83206b59e83dc1048259236db68e9a16
        {
            //kiem tra so luong của sản phẩm >= so sl sp đó trong giỏ hàng
            var updatedQuantity = existingItem.quantity + cartItem.quantity

            if(cartItem.SoLuongSP <= updatedQuantity)
            {
                updatedQuantity = cartItem.SoLuongSP
                cartDao.updateCartItem(existingItem.copy(quantity = updatedQuantity))
            }
<<<<<<< HEAD
=======
>>>>>>> bcd2a864b3006d63cba77752185182e112377e7b
>>>>>>> 8d37676d83206b59e83dc1048259236db68e9a16
            cartDao.updateCartItem(existingItem.copy(quantity = updatedQuantity))
            }
        } else {
            cartDao.insertCartItem(cartItem)
        }
    }
}


