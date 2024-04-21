package com.dev.ramgdev.roversmars

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dev.ramgdev.roversmars.nav.Action
import com.dev.ramgdev.roversmars.nav.Destinations.Home
import com.dev.ramgdev.roversmars.nav.Destinations.Manifest
import com.dev.ramgdev.roversmars.ui.theme.MarsRoverTheme
import com.dev.ramgdev.roversmars.ui.theme.view.ManifestScreen
import com.dev.ramgdev.roversmars.ui.theme.view.RoverList

@Composable
fun NavCompose() {
    val navController = rememberNavController()
    val actions = remember(navController) { Action(navController) }

    MarsRoverTheme {
        NavHost(navController = navController, startDestination = Home) {
            composable(Home) {
                RoverList() { roverName ->
                    actions.manifest(roverName)
                }
            }
            composable(Manifest) {backStackEntry ->
                ManifestScreen(
                    roverName = backStackEntry.arguments?.getString("roverName")
                )
            }
        }
    }
}