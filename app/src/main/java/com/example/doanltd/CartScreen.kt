package com.example.doanltd

import Screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController) {
    var soluong by remember { mutableStateOf(1) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Giỏ hàng") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Column {
                // Tổng giá và nút thanh toán
                Surface(
                    modifier = Modifier.fillMaxWidth().size(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("65.000đ")
                            Text(
                                "Giảm 10.500đ",
                                color = Color.Red
                            )
                        }
                        Button(
                            onClick = { /* Handle checkout */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Gray
                            )
                        ) {
                            Text("Thanh toán")
                        }
                    }
                }

                // Bottom navigation
//                NavigationBar {
//                    NavigationBarItem(
//                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
//                        label = { Text("Home") },
//                        selected = false,
//                        onClick = { navController.navigate(Screen.Home.route) }
//                    )
//                    NavigationBarItem(
//                        icon = { Icon(Icons.Default.Email, contentDescription = "Tin Nhắn") },
//                        label = { Text("Tin nhắn") },
//                        selected = false,
//                        onClick = { navController.navigate(Screen.Mesage.route) }
//                    )
//                    NavigationBarItem(
//                        icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Giỏ Hàng") },
//                        label = { Text("Giỏ Hàng") },
//                        selected = false,
//                        onClick = {navController.navigate(Screen.Cart.route) }
//                    )
//                    NavigationBarItem(
//                        icon = { Icon(Icons.Default.Person, contentDescription = "Thông Tin") },
//                        label = { Text("Thông Tin") },
//                        selected = false,
//                        onClick = { navController.navigate(Screen.Profile.route) }
//                    )
//                    NavigationBarItem(
//                        icon = { Icon(Icons.Default.Settings, contentDescription = "Cài Đặt") },
//                        label = { Text("Cài Đặt") },
//                        selected = false,
//                        onClick = { navController.navigate(Screen.Setting.route) }
//                    )
//                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            item {
                // Cart item
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Product image
                    Image(
                        painter = painterResource(id = R.drawable.anh1),
                        contentDescription = "",
                        modifier = Modifier.size(80.dp)
                    )

                    // Product details
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            "[COMBO 6] Bánh Tráng Phơi Sương Sốt Tắc Muối Ruốc Hành Phí",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text("COMBO 6")

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    "65.000đ",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    "85.000đ",
//                                    style = MaterialTheme.typography.bodySmall.copy(
//                                        textDecoration = TextDecoration.LineThrough
//                                    ),
                                    color = Color.Gray
                                )
                            }

                            // Quantity controls
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                IconButton(
                                    onClick = { if (soluong > 1) soluong-- },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(Icons.Filled.KeyboardArrowLeft, "Decrease")
                                }
                                Text(soluong.toString())
                                IconButton(
                                    onClick = { soluong++ },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(Icons.Default.Add, "Increase")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



