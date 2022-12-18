package com.dion.jetshop.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dion.jetshop.R
import com.dion.jetshop.ui.navigation.NavigationItem
import com.dion.jetshop.ui.navigation.Screen
import com.dion.jetshop.ui.screen.cart.CartScreen
import com.dion.jetshop.ui.screen.detail.DetailScreen
import com.dion.jetshop.ui.screen.home.HomeScreen
import com.dion.jetshop.ui.screen.profile.ProfileScreen
import com.dion.jetshop.ui.theme.JetShopTheme

@Composable
fun JetShopApp(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(bottomBar = {
        if (currentRoute != Screen.Detail.route) {
            BottomBar(navHostController = navHostController)
        }
    }, modifier = modifier) { innerPadding ->
        NavHost(
            navController = navHostController, startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Screen.Home.route) {
                HomeScreen(navigateToDetail = {furnitureId ->
                    navHostController.navigate(Screen.Detail.createRoute(furnitureId))
                })
            }

            composable(Screen.Cart.route) {
                CartScreen()
            }

            composable(Screen.Profile.route) {
                ProfileScreen()
            }

            composable(Screen.Detail.route, arguments = listOf(navArgument("id"){
                type = NavType.LongType
            })) {
                val id = it.arguments?.getLong("id") ?: -1L
                DetailScreen(
                    furnitureId = id,
                    navigateBack = {navHostController.navigateUp()},
                navigateToCart = {
                    navHostController.popBackStack()
                    navHostController.navigate(Screen.Cart.route){
                        popUpTo(navHostController.graph.findStartDestination().id){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
            }
        }
    }
}


@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    BottomNavigation(modifier = modifier) {
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(id = R.string.menu_home),
                icon = Icons.Rounded.Home,
                screen = Screen.Home
            ),

            NavigationItem(
                title = stringResource(id = R.string.menu_cart),
                icon = Icons.Rounded.ShoppingCart,
                screen = Screen.Cart
            ),

            NavigationItem(
                title = stringResource(id = R.string.menu_profile),
                icon = Icons.Rounded.AccountCircle,
                screen = Screen.Profile
            )
        )

        BottomNavigation {
            navigationItems.map { item ->
                BottomNavigationItem(
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navHostController.navigate(item.screen.route) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                saveState = true
                            }

                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                    label = { Text(text = item.title) },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    }
                )
            }
        }
    }
}