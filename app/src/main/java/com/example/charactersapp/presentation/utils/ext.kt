package com.example.charactersapp.presentation.utils

import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import com.example.charactersapp.App


//
//internal fun Context.findActivity(): ComponentActivity {
//    var context = this
//    while (context is ContextWrapper) {
//        if (context is ComponentActivity) return context
//        context = context.baseContext
//    }
//    throw IllegalStateException("Picture in picture should be called in the context of an Activity")
//}

val LocalNavController =
    staticCompositionLocalOf<NavController> { throw IllegalStateException("No NavController found") }


fun toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(App.instance, message, duration).show()
}