package com.example.charactersapp.presentation.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.charactersapp.presentation.theme.CharactersAppTheme
import com.example.charactersapp.presentation.utils.LocalNavController
import com.example.charactersapp.presentation.utils.animatedComposable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CharactersAppTheme {
                val navController = rememberNavController()

                CompositionLocalProvider(
                    LocalNavController provides navController,
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = CharactersFeed
                    ) {

                        animatedComposable<CharactersFeed>(
                            navController = navController
                        ) {
                            CharactersFeedScreen()
                        }

                        animatedComposable<CharacterDetails>(
                            navController = navController
                        ) {
                            CharacterDetailsScreen(it)
                        }
                    }
                }
            }
        }
    }
}