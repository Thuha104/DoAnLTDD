import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.doanltd.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = true,
                    onClick = { }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Email, contentDescription = "Tin Nhắn") },
                    label = { Text("Tin nhắn") },
                    selected = false,
                    onClick = { navController.navigate(Screen.Mesage.route)}
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Giỏ Hàng") },
                    label = { Text("Giỏ Hàng") },
                    selected = false,
                    onClick = {navController.navigate(Screen.Cart.route)}
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Thông Tin") },
                    label = { Text("Thông Tin") },
                    selected = false,
                    onClick = { navController.navigate(Screen.Profile.route)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Cài Đặt") },
                    label = { Text("Cài Đặt") },
                    selected = false,
                    onClick = {navController.navigate(Screen.Setting.route) }
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
            OutlinedTextField(
                value = "",
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                placeholder = { Text("Tìm Kiếm") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )



            // Danh Muc
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Danh Mục",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            // Icon Danh Muc
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                items(categories) { category ->
                    CategoryItem(category)
                }
            }

            // Danh Cho Ban
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Danh cho bạn",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Row {
                    repeat(3) { index ->
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(if (index == 0) Color(0xFFFF4B12) else Color.LightGray)
                                .padding(end = 4.dp)
                        )
                    }
                }
            }

            // Danh sach san pham
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(foodItems) { food ->
                    FoodItem(food)
                }
            }
        }
    }
}

@Composable
fun CategoryItem(category: Category) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(60.dp)
    ) {
        Card(
            modifier = Modifier.size(60.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Image(
                painter = painterResource(id = category.iconRes),
                contentDescription = category.name,
                modifier = Modifier.padding(12.dp)
            )
        }
        Text(
            text = category.name,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 4.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun FoodItem(food: FoodItem) {
    Card(
        modifier = Modifier.width(160.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = food.imageRes),
                contentDescription = food.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Text(
                text = food.name,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Text(
                    text = "• ${food.Description}Thơm ngon khó cưỡng",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "${food.price}VND",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFF4B12))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Add to cart",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}

data class Category(
    val name: String,
    val iconRes: Int
)

data class FoodItem(
    val name: String,
    val Description: String,
    val price: Double,
    val imageRes: Int
)

val categories = listOf(
    Category("Bánh tráng", R.drawable.logo),
    Category("Muối", R.drawable.logo),
    Category("Khô", R.drawable.logo),
    Category("Đồ đóng gói", R.drawable.logo),
    Category("Khác", R.drawable.logo)
)

val foodItems = listOf(
    FoodItem("Bánh tráng phơi sương", "", 50.00, R.drawable.logo),
    FoodItem("Muối nhuyễn", "", 10.00, R.drawable.logo),
    FoodItem("Muối sặc", "", 10.00, R.drawable.logo),
    FoodItem("Khô Bò", "", 10.00, R.drawable.logo),
    FoodItem("Khô Gà", "", 10.00, R.drawable.logo),
    FoodItem("Cá Viên", "", 10.00, R.drawable.logo),
    FoodItem("Bò Viên", "", 10.00, R.drawable.logo)
)