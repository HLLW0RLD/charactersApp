package com.example.charactersapp.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.charactersapp.presentation.screen.viewModel.CharactersFeedViewModel
import com.example.charactersapp.presentation.utils.LocalNavController
import org.koin.androidx.compose.koinViewModel

object CharactersFeed

@Composable
fun CharactersFeedScreen() {

    Scaffold(
        topBar = {

        },
        content = {
            CharactersFeed(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            )
        },
    )
}

@Composable
fun CharactersFeed(
    modifier: Modifier,
    charactersFeedViewModel: CharactersFeedViewModel = koinViewModel()
) {

    val navController = LocalNavController.current


}