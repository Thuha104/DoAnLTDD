package com.example.doanltd

import Screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.doanltd.CartManager.cartItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController) {
    var soluong by remember { mutableStateOf(1) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Giỏ hàng") },
            )
        },
        bottomBar = {
            Column {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Absolute.Right,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.align(Alignment.CenterVertically)
                        ) {
                            Text(
                                "65.000đ",
                                textAlign = TextAlign.Right,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                "Giảm 10.500đ",
                                color = Color.Red,
                                textAlign = TextAlign.Right,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        Button(
                            onClick = { /* Handle checkout */ },
                            modifier = Modifier
                                .padding(vertical = 8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red
                            )
                        ) {
                            Text("Thanh toán")
                        }
                    }
                }

                 ///Bottom navigation
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
                        onClick = { navController.navigate(Screen.Mesage.route) }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Giỏ Hàng") },
                        label = { Text("Giỏ Hàng") },
                        selected = false,
                        onClick = {navController.navigate(Screen.Cart.route) }
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
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            items(cartItems) { item ->
                CartItemComponent(item)
            }
        }
    }
}

@Composable
fun CartItemComponent(item: CartItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.name,
            modifier = Modifier.size(80.dp)
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                item.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "${item.price.toInt()}đ",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFFF4B12)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { CartManager.updateQuantity(item.id, item.quantity - 1) },
                    enabled = item.quantity > 1
                ) {
                    Icon(Icons.Default.Remove, contentDescription = "Decrease")
                }
                Text(
                    text = item.quantity.toString(),
                    modifier = Modifier.padding(horizontal = 8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
                IconButton(
                    onClick = { CartManager.updateQuantity(item.id, item.quantity + 1) }
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Increase")
                }
            }
        }
        IconButton(
            onClick = { CartManager.removeFromCart(item.id) }
        ) {
            Icon(Icons.Default.Delete, contentDescription = "Remove")
        }
    }
}




