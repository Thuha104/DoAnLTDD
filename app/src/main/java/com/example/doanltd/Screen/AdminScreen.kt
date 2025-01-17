import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adjust
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.doanltd.AppDatabase
import com.example.doanltd.Navigation.Screen
import com.example.doanltd.R
import com.example.doanltd.RoomDatabase.NgDungRoom.NgDungEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(navController: NavController) {
    val orders = remember {
        mutableStateListOf(
            Order("1", "Người Dùng 1", "[COMBO 6] Bánh Tráng Phơi Sương Sốt Tắc Muối Ruốc Hành Phi", 65000.0, 2, OrderStatus.ChuaDuyet),
            Order("2", "Người Dùng 2", "[COMBO 4] Bánh Tráng Phơi Sương Sốt Tắc Muối Ruốc Hành Phi", 45000.0, 1, OrderStatus.ChuaDuyet)
        )
    }

    var user by remember { mutableStateOf<NgDungEntity?>(null) }
    val context = LocalContext.current
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Admin") }
            )
        },
        bottomBar ={
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Adjust, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = true,
                    onClick = {  navController.navigate(Screen.Login.route) {
                        CoroutineScope(Dispatchers.IO).launch { user?.let { db.delete(it) } }
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }}
                )
            }
        }

    ) { paddingValues ->
//        Button(
//            onClick = {
//                navController.navigate(Screen.Login.route) {
//                    CoroutineScope(Dispatchers.IO).launch { user?.let { db.delete(it) } }
//                    popUpTo(Screen.Login.route) { inclusive = true }
//                }
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4B12))
//        ) {
//            Text("Đăng Xuất")
//        }
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(orders) { order ->
                OrderItem(order, navController)
            }
        }

    }
}

@Composable
fun OrderItem(order: Order, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = order.productName,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2
                )
                Text(
                    text = "${order.price}đ",
                    color = Color.Red
                )
                Text(
                    text = "SL: ${order.quantity}"
                )
                Text(
                    text = "Status: ${order.status.description}",
                    fontStyle = FontStyle.Italic
                )
            }
            var expanded by remember { mutableStateOf(false) }
            val statusOptions = OrderStatus.values().filter { it != OrderStatus.ChuaDuyet }

            Box {
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "Change Order Status"
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    statusOptions.forEach { status ->
                        DropdownMenuItem(
                            text = { Text(status.description) },
                            onClick = {
                                order.status = status
                                expanded = false
                                // TODO: Update order status in ViewModel/Database
                            }
                        )
                    }
                }
            }
        }
    }
}

data class Order(
    val id: String,
    val tenNgD: String,
    val productName: String,
    val price: Double,
    val quantity: Int,
    var status: OrderStatus = OrderStatus.ChuaDuyet
)

enum class OrderStatus(val description: String) {
    ChuaDuyet("Chưa Duyệt"),
    DuyetDon("Duyệt Đơn"),
    HuyDon("Hủy Đơn"),
    DangGiao("Đang Giao"),
    DaGiao("Đã Giao"),
    ChuaGiao("Chưa Giao")
}
