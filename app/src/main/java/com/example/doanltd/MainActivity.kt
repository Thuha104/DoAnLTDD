package com.example.doanltd

import com.example.doanltd.Navigation.AuthNavigation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.doanltd.ui.theme.DoAnLTDĐTheme

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
