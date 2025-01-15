package com.example.doanltd

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
<<<<<<< HEAD
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph
=======
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
>>>>>>> d7dd8f80d2a134ac95f41b9bb40b3f168decfc9a
import androidx.navigation.compose.rememberNavController
import com.example.doanltd.Navigation.AuthNavigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.doanltd.ui.theme.DoAnLTDĐTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()  // Đảm bảo giao diện full màn hình

        setContent {
            DoAnLTDĐTheme {
                AuthNavigation()
            }
        }
    }
}

