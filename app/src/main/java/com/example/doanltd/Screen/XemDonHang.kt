package com.example.doanltd.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.doanltd.AppDatabase
import com.example.doanltd.RoomDatabase.NgDungRoom.NgDungEntity
import com.example.doanltd.View.SanPhamViewModel
import com.example.doanltd.data.HoaDon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun XemDonHangScreen(navController: NavController, viewModel: SanPhamViewModel = viewModel()) {
    val hoaDons by remember { derivedStateOf { viewModel.hoadons } }

    val context = LocalContext.current
    var user by remember { mutableStateOf<NgDungEntity?>(null) }
    val db = AppDatabase.getDatabase(context).ngDungDao()

    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val userList = db.getAll()
            if (userList.isNotEmpty()) {
                withContext(Dispatchers.Main) {
                    user = userList[0]
                }
            }
        }
    }

    val filteredHoaDons = hoaDons.filter { it.MaNgD == user?.MaNgD }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Danh sách đơn hàng",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(filteredHoaDons) { hoadon ->
                    OrderItem(hoadon, navController)
                }
            }
        }
    }
}

@Composable
private fun OrderItem(hoaDon: HoaDon, navController: NavController) {
    val formattedPrice = NumberFormat.getInstance(Locale("vi", "VN")).format(hoaDon.TongTien)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Trạng thái đơn hàng
            Text(
                text = "Trạng thái: ${hoaDon.TrangThai}",
                color = Color(0xFFFF424F),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Địa chỉ giao hàng
            Text(text = "Địa chỉ: ${hoaDon.DiaChi}")

            Spacer(modifier = Modifier.height(8.dp))

            // Tổng tiền
            Text(
                text = "Tổng tiền: $formattedPrice",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Nút hủy đơn hàng (hiển thị nếu trạng thái là "Đã đặt" hoặc "Đặt hàng thành công")
            if (hoaDon.TrangThai == "Đã đặt" || hoaDon.TrangThai == "Đặt hàng thành công") {
                Button(
                    onClick = { huyDonHang(hoaDon.MaHD) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Hủy đơn hàng", color = Color.White)
                }
            }
        }
    }
}

// Hàm xử lý hủy đơn hàng
private fun huyDonHang(maHD: String) {
    // TODO: Thêm logic gọi API hoặc cập nhật trạng thái đơn hàng trong ViewModel
}
