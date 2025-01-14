object CartManager {
    private val cartItems = mutableListOf<CartItem>()

    // Thêm sản phẩm vào giỏ
    fun addToCart(item: CartItem) {
        val existingItem = cartItems.find { it.id == item.id }
        if (existingItem != null) {
            // Nếu sản phẩm đã có trong giỏ, tăng số lượng
            val index = cartItems.indexOf(existingItem)
            cartItems[index] = existingItem.copy(quantity = existingItem.quantity + item.quantity)
        } else {
            // Nếu chưa có, thêm sản phẩm vào giỏ
            cartItems.add(item)
        }
    }

    // Trả về danh sách sản phẩm trong giỏ hàng
    fun getCartItems(): List<CartItem> = cartItems // Chỉ trả về List<CartItem> thay vì MutableList

    // Cập nhật số lượng của sản phẩm trong giỏ
    fun updateQuantity(item: CartItem, quantity: Int) {
        val index = cartItems.indexOf(item)
        if (index >= 0) {
            cartItems[index] = cartItems[index].copy(quantity = quantity)
        }
    }

    // Xóa sản phẩm khỏi giỏ hàng
    fun removeFromCart(item: CartItem) {
        cartItems.remove(item)
    }

    // Xóa tất cả sản phẩm trong giỏ hàng
    fun clearCart() {
        cartItems.clear()
    }

    // Tính tổng giá trị giỏ hàng
    fun getTotalPrice(): Double {
        val total = cartItems.sumOf { it.price * it.quantity }
        cartItems.forEach {
            println("Product: ${it.name}, Price: ${it.price}, Quantity: ${it.quantity}, Subtotal: ${it.price * it.quantity}")
        }
        println("Total Price: $total")
        return total
    }

        fun updateQuantity(itemId: String, newQuantity: Int) {
            val item = cartItems.find { it.id == itemId }
            item?.let {
                it.quantity = newQuantity
            }
    }

}

data class CartItem(
    val id: String,           // ID sản phẩm
    val name: String,         // Tên sản phẩm
    val price: Double,        // Giá sản phẩm
    val imageUrl: String,     // URL hình ảnh sản phẩm
    var quantity: Int = 1    // Số lượng sản phẩm trong giỏ (mặc định là 1)
)
