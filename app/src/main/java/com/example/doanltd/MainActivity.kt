package com.example.doanltd

//import com.example.doanltd.Navigation.AuthNavigation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph
import androidx.navigation.compose.rememberNavController
import com.example.doanltd.Navigation.AuthNavigation
//import com.example.doanltd.Navigation.Navigation
import com.example.doanltd.ui.theme.DoAnLTDĐTheme
import java.lang.reflect.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DoAnLTDĐTheme {
                    AuthNavigation()
            }
        }
    }
}

