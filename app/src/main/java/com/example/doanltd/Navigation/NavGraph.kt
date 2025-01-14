package com.example.doanltd.Navigation

import CartItem
import CartScreen
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.doanltd.Screen.CategoryScreen
import com.example.doanltd.Screen.ChatScreen
import com.example.doanltd.Screen.HomeScreen
import com.example.doanltd.Screen.LoginScreen
import com.example.doanltd.Screen.MessageScreen
import com.example.doanltd.Screen.OrderDetailsScreen
import com.example.doanltd.Screen.OrderHistoryScreen
import com.example.doanltd.Screen.ProductDetailScreen
import com.example.doanltd.Screen.ProfileScreen
import com.example.doanltd.Screen.RegisterScreen
import com.example.doanltd.Screen.ReviewScreen
import com.example.doanltd.Screen.SettingScreen
import com.google.gson.Gson



sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Mesage: Screen("message")
    object Chat: Screen("chat")
    object Setting: Screen("setting")
    object Cart: Screen("cart")
    object OrderDetails : Screen("order_details")
    object ProductDetail : Screen("productdetail")
    object OrderHistory : Screen("order_history")
    object Review : Screen("review/{productId}")
    object CategoryScreen:Screen("category/{categoryId}"){
        fun createRoute(categoryId: String): String = "category/$categoryId"
    }
}

@Composable
fun AuthNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.Register.route) {
            RegisterScreen(navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }
        composable(Screen.Mesage.route) {
            MessageScreen(navController)
        }
        composable(Screen.Chat.route) {
            ChatScreen(navController)
        }
        composable(Screen.Setting.route) {
            SettingScreen(navController)
        }
        composable(Screen.Cart.route){
            CartScreen(navController)
        }
        composable(Screen.OrderDetails.route) {
            OrderDetailsScreen(navController)
        }
//        composable(Screen.ProductDetail.route) {
//            ProductDetailScreen(navController)
//        }
        composable(
            route = "${Screen.ProductDetail.route}/{id}", // Định nghĩa route với tham số `id`
            arguments = listOf(navArgument("id") { type = NavType.StringType }) // Khai báo loại của tham số
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") // Lấy giá trị `id`
            ProductDetailScreen(navController = navController, productId = id)
        }
        composable(Screen.OrderHistory.route) {
            OrderHistoryScreen(navController)
        }
        composable(
            route = Screen.Review.route,
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            ReviewScreen(navController, productId)
        }
        composable(
            route = Screen.CategoryScreen.route,
            arguments = listOf(navArgument("categoryId") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")
            CategoryScreen(
                navController = navController,
                categoryId = categoryId
            )
        }
    }
}
