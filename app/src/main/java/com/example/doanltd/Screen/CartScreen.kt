

import CartItem
import CartManager.updateQuantity
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.doanltd.Navigation.Screen
import com.google.gson.Gson


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController) {
    val cartItems = CartManager.getCartItems()
    val (totalPrice, setTotalPrice) = remember { mutableStateOf(CartManager.getTotalPrice()) }

    LaunchedEffect(Unit) {
        Log.d("CartScreen", "Screen loaded with ${cartItems.size} items")
    }

    if (cartItems.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Giỏ hàng trống", style = MaterialTheme.typography.bodyLarge)
        }
        return
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
                    onClick = { navController.navigate(Screen.Mesage.route) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Giỏ Hàng") },
                    label = { Text("Giỏ Hàng") },
                    selected = true,
                    onClick = { /* Do nothing, already on this screen */ }
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(cartItems) { cartItem ->
                    CartItemView(
                        cartItem = cartItem,
                        quantity = cartItem.quantity,
                        onQuantityChange = { newQuantity ->
                            CartManager.updateQuantity(cartItem.id, newQuantity)
                            setTotalPrice(CartManager.getTotalPrice())
                        }
                    )
                }
            }

            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Tổng cộng:", style = MaterialTheme.typography.bodyLarge)
                    Text(
                        "${totalPrice.toInt()}đ",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFF4B12)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        navController.navigate(Screen.OrderDetails.route)
//                        val gson = Gson()
//                        val cartJson = gson.toJson(cartItems)
//                        val encodedJson = Uri.encode(cartJson)
//                        Log.d("CartScreen", "Navigating to OrderDetails with ${cartItems.size} items")
//                        val route = "orderDetails/$encodedJson/$totalPrice"
//                        Log.d("CartScreen", "Navigation route: $route")
//                        navController.navigate(route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4B12))
                ) {
                    Text("Thanh toán", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun CartItemView(cartItem: CartItem, quantity: Int, onQuantityChange: (Int) -> Unit) {
    var currentQuantity by remember { mutableStateOf(quantity) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = cartItem.imageUrl,
            contentDescription = "Product Image",
            modifier = Modifier.size(80.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        ) {
            Text(cartItem.name, style = MaterialTheme.typography.bodyLarge)
            Text("${cartItem.price.toInt()}đ", style = MaterialTheme.typography.titleMedium, color = Color(0xFFFF4B12))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    if (currentQuantity > 1) {
                        currentQuantity -= 1
                        onQuantityChange(currentQuantity)
                    }
                },
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier.size(32.dp)
            ) {
                Text("-")
            }

            Text("$currentQuantity", modifier = Modifier.padding(horizontal = 8.dp))

            Button(
                onClick = {
                    currentQuantity += 1
                    onQuantityChange(currentQuantity)
                },
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier.size(32.dp)
            ) {
                Text("+")
            }
        }
    }

    Divider(modifier = Modifier.padding(vertical = 8.dp))
}
