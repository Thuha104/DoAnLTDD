package com.example.doanltd.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.doanltd.Screen.CartScreen
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
        composable(Screen.ProductDetail.route) {
            ProductDetailScreen(navController)
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
    }
}