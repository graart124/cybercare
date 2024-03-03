package com.example.hatakon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.hatakon.screens.NavGraphs
import com.example.hatakon.screens.bottombar.BottomNavigationBar
import com.example.hatakon.ui.theme.HatakonTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HatakonTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = { BottomNavigationBar(navController = navController) }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = it.calculateBottomPadding())
                    ) {
                        DestinationsNavHost(
                            navController = navController,
                            navGraph = NavGraphs.root
                        )
                    }
                }
            }
        }
    }
}