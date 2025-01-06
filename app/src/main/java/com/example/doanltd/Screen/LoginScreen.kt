package com.example.doanltd.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.doanltd.Navigation.Screen
import com.example.doanltd.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var userType by remember { mutableStateOf<String>("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top Bar
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Đăng Nhập",
                        color = Color.Black,
                        modifier = Modifier.weight(0.9f) // Đẩy các thành phần khác sang bên phải
                    )
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier.size(60.dp).clip(CircleShape)
                    )
                }
            }
        )


        // Tab Layout
        TabRow(
            selectedTabIndex = 0,
            containerColor = Color(0xFF6D88F4),
            contentColor = Color.White
        ) {
            Tab(
                selected = true,
                onClick = { },
                text = { Text("Đăng Nhập") }
            )
            Tab(
                selected = false,
                onClick = { navController.navigate("register") },
                text = { Text("Đăng Ký") }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "\t\tWelcome to \n Út Khờ Snack",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = Color(0xFF6D88F4),
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 24.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        tint = Color(0xFF6D88F4)
                    )
                }
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        tint = Color(0xFF6D88F4)
                    )
                }
            )

            Text(
                "Quên mật khẩu?",
                color = Color(0xFFFF4B12),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(vertical = 8.dp)
            )

            Row(
                modifier = Modifier.padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Bạn chưa có tài khoản")
                Text(
                    "Đăng ký ngay",
                    color = Color(0xFFFF4B12),
                    modifier = Modifier.clickable {
                        navController.navigate("register")
                    }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { userType = if (userType != "NguoiDung") "NguoiDung" else "" }
                ) {
                    Checkbox(
                        checked = userType == "NguoiDung",
                        onCheckedChange = { userType = if (it) "NguoiDung" else "" },
                        colors = CheckboxDefaults.colors(checkedColor = Color(0xFFFF4B12))
                    )
                    Text("Người Dùng")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { userType = if (userType != "NhanVien") "NhanVien" else "" }
                ) {
                    Checkbox(
                        checked = userType == "NhanVien",
                        onCheckedChange = { userType = if (it) "NhanVien" else "" },
                        colors = CheckboxDefaults.colors(checkedColor = Color(0xFFFF4B12))
                    )
                    Text("Nhân Viên")
                }
            }


            Button(
                onClick = { navController.navigate(Screen.Home.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D88F4)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("ĐĂNG NHẬP", color = Color.White)
            }
        }
    }
}

