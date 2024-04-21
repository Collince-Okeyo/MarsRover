package com.dev.ramgdev.roversmars.nav

import androidx.navigation.NavController
import com.dev.ramgdev.roversmars.nav.Destinations.Home

object Destinations {
    const val Home = "home"
    const val Manifest = "manifest/{roverName}"
}

class Action(navController: NavController) {
    val home: () -> Unit = { navController.navigate(Home) }
    val manifest: (roverName: String) -> Unit = { roverName ->
        navController.navigate("manifest/{$roverName}")
    }
}