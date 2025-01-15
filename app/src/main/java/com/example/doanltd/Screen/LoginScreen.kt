package com.example.doanltd.Screen

import NgDung
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.doanltd.AppDatabase
import com.example.doanltd.Navigation.Screen
import com.example.doanltd.R
import com.example.doanltd.View.AuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController,viewModel: AuthViewModel= androidx.lifecycle.viewmodel.compose.viewModel()) {
    var TKNgD by remember { mutableStateOf("") }
    var MatKhauNgD by remember { mutableStateOf("") }
    val dangNhapThanhCong by viewModel.dangNhapThanhCong.collectAsState()
    val dulieunguoidung by viewModel.duLieuNguoiDung.collectAsState()
    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context).ngDungDao()

//    val isChecked = remember { mutableStateOf(false) }
//
//    LaunchedEffect(isChecked.value) {
//        if (!isChecked.value) {
//            // Di chuyển việc truy vấn vào coroutine
//            CoroutineScope(Dispatchers.IO).launch {
//                val userList = db.getAll()
//                if (userList.isNotEmpty()) {
//                    withContext(Dispatchers.Main) {
//                        navController.navigate("home") // Chuyển về màn hình home
//                    }
//                } else {
//                    withContext(Dispatchers.Main) {
//                        navController.navigate("login") // Chuyển về màn hình đăng nhập
//                    }
//                }
//            }
//            isChecked.value = true // Đánh dấu đã kiểm tra xong, không chạy lại nữa
//        }
//    }

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
                value = TKNgD,
                onValueChange = { TKNgD = it },
                label = { Text("Tên đăng nhập") },
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
                value = MatKhauNgD,
                onValueChange = { MatKhauNgD = it },
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
                Text("Bạn chưa có tài khoản? ")
                Text(
                    "Đăng ký ngay",
                    color = Color(0xFFFF4B12),
                    modifier = Modifier.clickable {
                        navController.navigate("register")
                    }
                )
            }

            Button(
                onClick = { CoroutineScope(Dispatchers.IO).launch {
                    viewModel.dangNhapNguoiDung(
                        tkNgD = TKNgD,
                        matKhauNgD = MatKhauNgD
                    )
                } },
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
    // ⬇️ Đặt LaunchedEffect bên ngoài Column ⬇️
    LaunchedEffect(dangNhapThanhCong) {
        dangNhapThanhCong?.let {
            if (it) {
                dulieunguoidung?.let { user ->
                    db.insertUserByFields(
                        user.MaNgD,
                        user.TenNgD,
                        user.Email,
                        user.SDT,
                        user.TKNgD,
                        user.TrangThai,
                        user.ChucVu
                    )
                    if(user.ChucVu.equals("NguoiDung"))
                    {
                        navController.navigate("home")
                    }
                    else
                    {
                        navController.navigate("admin")
                    }
                }

                Toast.makeText( context,"Đăng nhập thành công!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context,"Đăng nhập thất bại!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

