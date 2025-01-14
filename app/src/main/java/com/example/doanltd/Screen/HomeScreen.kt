package com.example.doanltd.Screen

import LoaiSP
import com.example.doanltd.data.SanPham
import com.example.doanltd.View.SanPhamViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.doanltd.Navigation.Screen
import com.example.doanltd.Navigation.Screen.CategoryScreen
import com.example.doanltd.R
import java.text.NumberFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: SanPhamViewModel = viewModel()) {
    var searchQuery by remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }
    var resetHome by remember { mutableStateOf(false) }
    val sanPhams by remember { derivedStateOf { viewModel.posts } }
    val loaiSps by remember { derivedStateOf { viewModel.loaisanphams } }

    // Reset Home when clicked on Home Button
    if (resetHome) {
        searchQuery = ""
        isSearching = false
        resetHome = false
    }

    fun normalize(text: String): String {
        val regex = "\\p{InCombiningDiacriticalMarks}+".toRegex()
        val temp = java.text.Normalizer.normalize(text, java.text.Normalizer.Form.NFD)
        return regex.replace(temp, "").lowercase()
    }

    val filteredSanPhams = sanPhams.filter {
        normalize(it.TenSp).contains(normalize(searchQuery)) ||
                normalize(it.MoTa).contains(normalize(searchQuery))
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = true,
                    onClick = { resetHome = true }
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
                    onClick = { navController.navigate(Screen.Cart.route) }
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
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        "Út Khờ Snack",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "Ăn uống là nhất",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier.size(90.dp)
                    )
                }
            }

            // Search Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .weight(1f)
                        .onFocusChanged { focusState -> isSearching = focusState.isFocused },
                    placeholder = { Text("Tìm Kiếm") },
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )
                if (isSearching) {
                    IconButton(
                        onClick = { isSearching = false },
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                }
            }

            // Category Section
            Text(
                "Danh Mục",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)

            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                items(loaiSps) { loaiSp ->
                    CategoryItem(loaiSP = loaiSp, navController = navController)
                }
            }

            // Product Section
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(if (searchQuery.isNotEmpty()) filteredSanPhams else sanPhams) { sanPham ->
                    SanPhamItem(sanPham = sanPham, navController = navController)
                }
            }
        }
    }
}

@Composable
fun CategoryItem(loaiSP: LoaiSP, navController: NavController) {
    val maLoai = loaiSP.MaLoai ?: return

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(60.dp)
            .clickable {
                navController.navigate(CategoryScreen.createRoute(maLoai))
            }
    ) {
        Card(
            modifier = Modifier.size(60.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = loaiSP.HinhLoai,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
        }
        Text(
            text = loaiSP.TenLoai,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 4.dp),
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun SanPhamItem(sanPham: SanPham, navController: NavController) {
    val formattedPrice = NumberFormat.getInstance(Locale("vi", "VN")).format(sanPham.DonGia)

    Card(
        modifier = Modifier.width(160.dp),
        shape = RoundedCornerShape(12.dp),
        onClick = { navController.navigate("${Screen.ProductDetail.route}/${sanPham.MaSp}") }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = sanPham.HinhSp,
                contentDescription = null
            )
            Text(
                text = sanPham.TenSp,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = "${sanPham.MoTa} Thơm ngon khó cưỡng",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = "$formattedPrice VND",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

