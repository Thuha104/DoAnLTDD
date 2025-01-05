package com.example.doanltd

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

data class CartItem(
    val id: String,
    val name: String,
    val price: Double,
    val imageRes: Int,
    var quantity: Int = 1
)

object CartManager {
    var cartItems by mutableStateOf(listOf<CartItem>())
        private set

    fun addToCart(item: CartItem) {
        val existingItem = cartItems.find { it.id == item.id }
        if (existingItem != null) {
            cartItems = cartItems.map {
                if (it.id == item.id) it.copy(quantity = it.quantity + 1)
                else it
            }
        } else {
            cartItems = cartItems + item
        }
    }

    fun removeFromCart(itemId: String) {
        cartItems = cartItems.filter { it.id != itemId }
    }

    fun updateQuantity(itemId: String, newQuantity: Int) {
        cartItems = cartItems.map {
            if (it.id == itemId) it.copy(quantity = newQuantity)
            else it
        }
    }

    fun clearCart() {
        cartItems = emptyList()
    }

    fun getTotal(): Double {
        return cartItems.sumOf { it.price * it.quantity }
    }
}