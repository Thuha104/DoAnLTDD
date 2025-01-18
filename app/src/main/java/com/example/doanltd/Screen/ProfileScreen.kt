package com.example.doanltd.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.doanltd.AppDatabase
import com.example.doanltd.Navigation.Screen
import com.example.doanltd.R
import com.example.doanltd.RoomDatabase.NgDungRoom.NgDungEntity
import com.example.doanltd.View.AuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.widget.Toast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
<<<<<<< HEAD
fun ProfileScreen(navController: NavController, viewModel: AuthViewModel = viewModel()) {
=======
fun ProfileScreen(navController: NavController) {
    //biến trạng thái LƯU thông tin ng.dung
>>>>>>> 19d1ff738a8f3c42e04a8e26108e8d30d4869bd8
    var user by remember { mutableStateOf<NgDungEntity?>(null) }
    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context).ngDungDao() // lấy DAO để truy vấn CSDL

    var showDialog by remember { mutableStateOf(false) }
    var updatedName by remember { mutableStateOf("") }
    var updatedPhone by remember { mutableStateOf("") }
    var updatedEmail by remember { mutableStateOf("") }
    var successMessage by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val userList = db.getAll() // lấy DS ngdung từ CSDL
            if (userList.isNotEmpty()) {
                withContext(Dispatchers.Main) {
<<<<<<< HEAD
                    user = userList[0]
                    updatedName = user?.TenNgD ?: ""
                    updatedPhone = user?.SDT ?: ""
                    updatedEmail = user?.Email ?: ""
=======
                    user = userList[0] // lấy ng.dung đầu tiên
>>>>>>> 19d1ff738a8f3c42e04a8e26108e8d30d4869bd8
                }
            }
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
                    selected = false,
                    onClick = { navController.navigate(Screen.Cart.route) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Thông Tin") },
                    label = { Text("Thông Tin") },
                    selected = true,
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
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
<<<<<<< HEAD
=======
            // Hiện image
>>>>>>> 19d1ff738a8f3c42e04a8e26108e8d30d4869bd8
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Profile Picture",
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // hiện TÊN & mô tả h.sơ
            Text(
                "User Profile",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                "Come and buy my products ! )",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            ProfileSection(
                title = "Thông Tin Cá Nhân",
                content = """
                    Họ và Tên: ${user?.TenNgD ?: ""}
                    Số Điện Thoại: ${user?.SDT ?: ""}
                    Email: ${user?.Email ?: ""}
                """.trimIndent(),
                onClick = { showDialog = true }
            )
            Spacer(modifier = Modifier.height(24.dp))

<<<<<<< HEAD
=======
            // Lịch Su Don Hang - Don Hang
>>>>>>> 19d1ff738a8f3c42e04a8e26108e8d30d4869bd8
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { navController.navigate(Screen.OrderHistory.route) }
                ) {
                    Icon(
                        imageVector = Icons.Default.History,
                        contentDescription = "Lịch sử đơn hàng",
                        modifier = Modifier.size(50.dp)
                    )
                    Text(
                        text = "Lịch sử đơn hàng",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { navController.navigate(Screen.XemDonHang.route) }
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingBag,
                        contentDescription = "Đơn hàng",
                        modifier = Modifier.size(50.dp)
                    )
                    Text(
                        text = "Đơn hàng",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Edit Profile") },
                text = {
                    Column {
                        TextField(
                            value = updatedName,
                            onValueChange = { updatedName = it },
                            label = { Text("Họ và Tên") }
                        )
                        TextField(
                            value = updatedPhone,
                            onValueChange = { updatedPhone = it },
                            label = { Text("Số điện thoại") }
                        )
                        TextField(
                            value = updatedEmail,
                            onValueChange = { updatedEmail = it },
                            label = { Text("Email") }
                        )
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            CoroutineScope(Dispatchers.IO).launch {
                                user?.let {
                                    it.TenNgD = updatedName
                                    it.SDT = updatedPhone
                                    it.Email = updatedEmail
                                    try {
                                        db.update(it)
                                        withContext(Dispatchers.Main) {
                                            viewModel.CapNhapNgDung(it.MaNgD, it.TenNgD, it.Email, it.SDT)
                                            successMessage = "Cập nhật thành công!"
                                            Toast.makeText(context, successMessage, Toast.LENGTH_SHORT).show()
                                            showDialog = false
                                        }
                                    } catch (e: Exception) {
                                        withContext(Dispatchers.Main) {
                                            errorMessage = "Lỗi: ${e.message}"
                                            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }
                            }
                        }
                    ) {
                        Text("Save")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

@Composable
fun ProfileSection(
    title: String,
    content: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "More",
                tint = Color.Gray
            )
        }
    }
}
