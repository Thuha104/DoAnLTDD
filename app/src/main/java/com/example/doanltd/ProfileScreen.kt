import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.doanltd.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = false,
                    onClick = {
                        navController.navigate(Screen.Home.route)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Email, contentDescription = "Tin Nhắn") },
                    label = { Text("Tin nhắn") },
                    selected = false,
                    onClick = {navController.navigate(Screen.Mesage.route) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Giỏ Hàng") },
                    label = { Text("Giỏ Hàng") },
                    selected = false,
                    onClick = { }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Thông Tin") },
                    label = { Text("Thông Tin") },
                    selected = true,
                    onClick = {

                        navController.navigate(Screen.Profile.route)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Cài Đặt") },
                    label = { Text("Cài Đặt") },
                    selected = false,
                    onClick = { }
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
            // Profile Image
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

            Text(
                "User Profile",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                "Come and bring me :)",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Information Section
            ProfileSection(
                title = "Thông tin liên hệ",
                content = "email123@email.com\n+84556487716",
                onClick = { }
            )

            ProfileSection(
                title = "Chức vụ",
                content = "Quản lý",
                onClick = { }
            )

            ProfileSection(
                title = "Địa chỉ",
                content = "192, Pham Don Tam, phuong 14, Quan 8",
                onClick = { }
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