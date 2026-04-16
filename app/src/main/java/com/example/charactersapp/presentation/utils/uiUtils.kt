package com.example.charactersapp.presentation.utils

import androidx.activity.compose.BackHandler
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute


inline fun <reified T : Any> NavGraphBuilder.animatedComposable(
    navController: NavController,
    crossinline content: @Composable (T) -> Unit
) {
    composable<T>(
        enterTransition = { enterTransition() },
        exitTransition = { exitTransition() },
        popEnterTransition = { popEnterTransition() },
        popExitTransition = { popExitTransition() }
    ) { backStackEntry ->

        BackHandler {
            navController.popBackStack()
        }
        val screen = backStackEntry.toRoute<T>()
        content(screen)
    }
}

fun enterTransition(): EnterTransition {
    return slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300))
}

fun exitTransition(): ExitTransition {
    return ExitTransition.None
}

fun popEnterTransition(): EnterTransition {
    return EnterTransition.None
}

fun popExitTransition(): ExitTransition {
    return slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300))
}