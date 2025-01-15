package com.example.doanltd.Screen

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.doanltd.AppDatabase
import com.example.doanltd.CartItem
import com.example.doanltd.toCartItem
import com.example.doanltd.toEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun CartScreen(navController: NavHostController) {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val cartDao = remember { db.cartDao() }
    val cartItems = remember { mutableStateOf<List<CartItem>>(emptyList()) }

    // Lấy danh sách sản phẩm từ cơ sở dữ liệu
    LaunchedEffect(Unit) {
        cartItems.value = cartDao.getAllCartItems().map { it.toCartItem() }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Giỏ hàng", style = MaterialTheme.typography.titleLarge)

        LazyColumn {
            items(cartItems.value) { cartItem ->
                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    Text("Tên sản phẩm: ${cartItem.name}")
                    Text("Giá: ${cartItem.price} VND")
                    Text("Số lượng: ${cartItem.quantity}")
                    Text("Hình ảnh: ${cartItem.imageUrl}")
                }
            }
        }

        Button(
            onClick = {
                val newCartItem = CartItem(0, "Sản phẩm mới", 100.0, 1, "image_url")
                CoroutineScope(Dispatchers.IO).launch {
                    cartDao.insertCartItem(newCartItem.toEntity())
                    cartItems.value = cartDao.getAllCartItems().map { it.toCartItem() }
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Thêm sản phẩm")
        }
    }
}
