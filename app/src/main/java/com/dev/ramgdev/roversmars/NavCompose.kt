package com.dev.ramgdev.roversmars

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dev.ramgdev.roversmars.nav.Action
import com.dev.ramgdev.roversmars.nav.Destinations.Home
import com.dev.ramgdev.roversmars.nav.Destinations.Manifest
import com.dev.ramgdev.roversmars.nav.Destinations.Photo
import com.dev.ramgdev.roversmars.nav.Destinations.Saved
import com.dev.ramgdev.roversmars.nav.Screen
import com.dev.ramgdev.roversmars.ui.theme.MarsRoverTheme
import com.dev.ramgdev.roversmars.ui.theme.view.ManifestScreen
import com.dev.ramgdev.roversmars.ui.theme.view.PhotoListSavedScreen
import com.dev.ramgdev.roversmars.ui.theme.view.PhotoScreen
import com.dev.ramgdev.roversmars.ui.theme.view.RoverList

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavCompose() {

    val items = listOf(
        Screen.Home, Screen.Saved
    )

    val navController = rememberNavController()
    val actions = remember(navController) { Action(navController) }

    MarsRoverTheme {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    val backStackEntry by rememberNavController().currentBackStackEntryAsState()
                    val currentDestination = backStackEntry?.destination

                    items.forEach { screen ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painter = painterResource(id = screen.drawableId),
                                    contentDescription = stringResource(id = screen.resourceId)
                                )
                            },
                            label = {
                                Text(text = stringResource(id = screen.resourceId))
                            },
                            selected = currentDestination?.hierarchy?.any{
                                if (it.route?.contains("manifest") == true || it.route?.contains("photo") == true) {
                                    screen.route == "home"
                                } else {
                                    it.route == screen.route
                                }
                            } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            NavHost(navController = navController, startDestination = Home) {
                composable(Home) {
                    RoverList(
                        modifier = modifier
                    ) { roverName ->
                        actions.manifest(roverName)
                    }
                }
                composable(Manifest) { backStackEntry ->
                    ManifestScreen(
                        modifier = modifier,
                        roverName = backStackEntry.arguments?.getString("roverName"),
                        marsRoverManifestViewModel = hiltViewModel(),
                        onClick = { roverName, sol ->
                            actions.photo(roverName, sol)
                        }
                    )
                }
                composable(Photo) { backStackEntry ->
                    PhotoScreen(
                        modifier = modifier,
                        roverName = backStackEntry.arguments?.getString("roverName"),
                        sol = backStackEntry.arguments?.getString("sol"),
                        marsRoverPhotoViewModel = hiltViewModel()
                    )
                }
                composable(Saved) {
                    PhotoListSavedScreen(
                        modifier = modifier
                    )
                }
            }

        }
    }
}