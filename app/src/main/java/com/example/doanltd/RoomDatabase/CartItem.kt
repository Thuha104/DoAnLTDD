package com.example.doanltd

data class CartItem(
    val id: Int,
    val name: String,
    val price: Double,
    val quantity: Int,
    val imageUrl: String
)
fun CartItemEntity.toCartItem(): CartItem {
    return CartItem(
        id = id,
        name = name,
        price = price,
        quantity = quantity,
        imageUrl = imageUrl
    )
}

fun CartItem.toEntity(): CartItemEntity {
    return CartItemEntity(
        id = id,
        name = name,
        price = price,
        quantity = quantity,
        imageUrl = imageUrl
    )
}
