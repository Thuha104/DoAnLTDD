package com.example.doanltd.Screen

<<<<<<< HEAD
import android.widget.Toast
import androidx.compose.foundation.clickable
=======
>>>>>>> 8d37676d83206b59e83dc1048259236db68e9a16
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
<<<<<<< HEAD
import androidx.compose.ui.layout.ContentScale
=======
>>>>>>> 8d37676d83206b59e83dc1048259236db68e9a16
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
<<<<<<< HEAD
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
=======
>>>>>>> 8d37676d83206b59e83dc1048259236db68e9a16

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun XemDonHangScreen(navController: NavController, viewModel: SanPhamViewModel = viewModel()) {
    val hoaDons by remember { derivedStateOf { viewModel.hoadons } }

    val context = LocalContext.current
    var user by remember { mutableStateOf<NgDungEntity?>(null) }
    val db = AppDatabase.getDatabase(context).ngDungDao()

<<<<<<< HEAD


=======
>>>>>>> 8d37676d83206b59e83dc1048259236db68e9a16
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
<<<<<<< HEAD
                    OrderItem(hoadon, navController,viewModel)
=======
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
>>>>>>> 8d37676d83206b59e83dc1048259236db68e9a16
                }
            }
        }
    }
}

<<<<<<< HEAD
@Composable
private fun OrderItem(hoaDon: HoaDon, navController: NavController,viewModel: SanPhamViewModel = viewModel()) {
    val formattedPrice = NumberFormat.getInstance(Locale("vi", "VN")).format(hoaDon.TongTien)
    var showPopup by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val capnhathoadonthanhcong  by viewModel.capnhathoadonthanhcong .collectAsState()
    val capnhathoadonthongbao by viewModel.capnhathoadonthongbao.collectAsState()
    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                showPopup = true
                viewModel.fetchChiTietHoaDon(hoaDon.MaHD) },
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
                    onClick = { CoroutineScope(Dispatchers.IO).launch {
                        viewModel.capnhathoadon(hoaDon.MaHD,"Đã hủy")
                    } },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Hủy đơn hàng", color = Color.White)
                }
            }
        }
    }
    if (showPopup) {
        OrderDetailsPopup(hoaDon = hoaDon, onDismiss = { showPopup = false },viewModel)
    }
    LaunchedEffect(capnhathoadonthongbao){
        capnhathoadonthanhcong?.let {
            if(it)
            {
                // thành công
                Toast.makeText( context,"$capnhathoadonthongbao", Toast.LENGTH_SHORT).show()
                //reaload
                navController.navigate("xem_don_hang")
            }
            else
            {
                //faild
                Toast.makeText( context,"$capnhathoadonthongbao", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


@Composable
private fun OrderDetailsPopup(hoaDon: HoaDon, onDismiss: () -> Unit,viewModel: SanPhamViewModel = viewModel()) {
    val chitiethoaDons by remember { derivedStateOf { viewModel.chitiethoadons } }
    val sanphams by remember { derivedStateOf { viewModel.posts } }
    Dialog(onDismissRequest = onDismiss) {
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
                Text(
                    text = "Chi tiết đơn hàng ${hoaDon.MaHD}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                LazyColumn {
                    items(chitiethoaDons) { chiTiet ->
                        val sanpham = sanphams.find { it.MaSp == chiTiet.MaSp }
                        if (sanpham != null) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = sanpham.HinhSp,
                                    contentDescription = "Hình ảnh sản phẩm",
                                    modifier = Modifier.size(80.dp),
                                    contentScale = ContentScale.Crop
                                )
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(start = 16.dp)
                                ) {
                                    Text(text = sanpham.TenSp, fontWeight = FontWeight.Bold)
                                    Text(text = "Giá: ${chiTiet.DonGia} VND")
                                    Text(text = "Số lượng: ${chiTiet.SLMua}")
                                }
                            }
                        }
                    }
                }


                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onDismiss,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Đóng")
                }
            }
        }
    }
}


=======
// Hàm xử lý hủy đơn hàng
private fun huyDonHang(maHD: String) {
    // TODO: Thêm logic gọi API hoặc cập nhật trạng thái đơn hàng trong ViewModel
}
>>>>>>> 8d37676d83206b59e83dc1048259236db68e9a16
