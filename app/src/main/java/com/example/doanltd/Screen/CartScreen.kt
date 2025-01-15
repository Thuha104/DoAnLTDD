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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.doanltd.AppDatabase
import com.example.doanltd.CartItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.doanltd.CartDao
import com.example.doanltd.CartManager
import com.example.doanltd.Navigation.Screen
import com.example.doanltd.toCartItem
import com.example.doanltd.toEntity
import kotlinx.coroutines.launch


@Composable
fun CartScreen(navController: NavHostController) {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val cartDao = remember { db.cartDao() }
    val cartItems = remember { mutableStateOf<List<CartItem>>(emptyList()) }
    val totalAmount = remember { mutableStateOf(0.0) }

    // Lấy danh sách sản phẩm từ cơ sở dữ liệu
    LaunchedEffect(Unit) {
        val items = cartDao.getAllCartItems().map { it.toCartItem() }
        cartItems.value = items
        totalAmount.value = items.sumOf { it.price * it.quantity }
    }

    fun updateCartItem(cartItem: CartItem, newQuantity: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            if (newQuantity > 0) {
                cartDao.updateCartItem(cartItem.copy(quantity = newQuantity).toEntity())
            } else {
                cartDao.deleteCartItem(cartItem.toEntity())
            }
            val items = cartDao.getAllCartItems().map { it.toCartItem() }
            cartItems.value = items
            totalAmount.value = items.sumOf { it.price * it.quantity }
        }
    }

    fun deleteCartItem(cartItem: CartItem) {
        CoroutineScope(Dispatchers.IO).launch {
            cartDao.deleteCartItem(cartItem.toEntity())
            val items = cartDao.getAllCartItems().map { it.toCartItem() }
            cartItems.value = items
            totalAmount.value = items.sumOf { it.price * it.quantity }
        }
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = false,
                    onClick = { navController.navigate(Screen.Home.route) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Email, contentDescription = "Tin Nhắn") },
                    label = { Text("Tin nhắn") },
                    selected = false,
                    onClick = { navController.navigate(Screen.Message.route) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Giỏ Hàng") },
                    label = { Text("Giỏ Hàng") },
                    selected = true, // Hiện tại đang chọn Giỏ hàng
                    onClick = { /* Không cần điều hướng */ }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Thông Tin") },
                    label = { Text("Thông Tin") },
                    selected = false,
                    onClick = { navController.navigate(Screen.Profile.route) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Cài Đặt") },
                    label = { Text("Cài Đặt") },
                    selected = false,
                    onClick = { navController.navigate(Screen.Setting.route) }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Đảm bảo nội dung không bị che bởi bottom bar
        ) {
            // Nội dung của giỏ hàng
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Trở về"
                    )
                }
                Text(
                    text = "Giỏ hàng",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            if (cartItems.value.isEmpty()) {
                Text(
                    text = "Giỏ hàng của bạn trống",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(cartItems.value) { cartItem ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp, horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = cartItem.imageUrl,
                                contentDescription = "Hình ảnh sản phẩm",
                                modifier = Modifier.size(80.dp),
                                contentScale = ContentScale.Crop
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .weight(1f)
                            ) {
                                Text(text = "Tên sản phẩm: ${cartItem.name}")
                                Text(text = "Giá: ${cartItem.price} VND")
                                Text(text = "Số lượng: ${cartItem.quantity}")
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(onClick = {
                                    if (cartItem.quantity > 1) {
                                        updateCartItem(cartItem, cartItem.quantity - 1)
                                    }
                                }) {
                                    Icon(Icons.Filled.Remove, contentDescription = "Giảm số lượng")
                                }
                                IconButton(onClick = {
                                    updateCartItem(cartItem, cartItem.quantity + 1)
                                }) {
                                    Icon(Icons.Filled.Add, contentDescription = "Tăng số lượng")
                                }
                                IconButton(onClick = {
                                    deleteCartItem(cartItem)
                                }) {
                                    Icon(
                                        Icons.Filled.Delete,
                                        contentDescription = "Xóa sản phẩm",
                                        tint = Color.Red
                                    )
                                }
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Tổng tiền: ${totalAmount.value} VND",
                        color = Color.Red,
                        style = MaterialTheme.typography.titleMedium,
                    )
                }

                Button(
                    onClick = { navController.navigate(Screen.OrderDetails.route) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Thanh toán", color = Color.White)
                }
            }
        }
    }
}
//
//
//
//
//
//
