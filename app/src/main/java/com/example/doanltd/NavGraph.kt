import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.doanltd.ChatScreen
import com.example.doanltd.MessageScreen



sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Mesage:Screen("message")
    object Chat:Screen("chat")
    object Setting:Screen("setting")

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
        composable(Screen.Chat.route) {
            SettingScreen(navController)
        }

    }
}