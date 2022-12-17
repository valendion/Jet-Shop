package com.dion.jetshop.ui.navigation

sealed class Screen(val route: String){
    object Home: Screen("home")
    object Profile: Screen("profile")
    object Cart: Screen("cart")
    object Detail: Screen("home/{id}"){
        fun createRoute(id: Long) = "home/$id"
    }
}
