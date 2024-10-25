package com.jabama.challenge.authentication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jabama.common.RouteConstant.LOGIN_ROUTE
import com.jabama.common.RouteConstant.SEARCH_ROUTE
import com.jabama.login.LoginRoute


@Composable
internal fun JabamaAppNavigation(isAuthenticated: Boolean) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (isAuthenticated) SEARCH_ROUTE else LOGIN_ROUTE
    ) {
        composable(route = LOGIN_ROUTE) {
            LoginRoute {
                navController.navigate(SEARCH_ROUTE)
            }
        }
        composable(route = SEARCH_ROUTE) {
            //TODO: Search screen
        }
    }
}