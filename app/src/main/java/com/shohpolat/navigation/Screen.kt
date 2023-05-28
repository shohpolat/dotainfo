package com.shohpolat.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route:String,
    val arguments:List<NamedNavArgument>
) {

    object HeroList: Screen("heroList", emptyList())

    object HeroDetail: Screen("heroDetail", listOf(
        navArgument("heroId") {
            type = NavType.IntType
        }
    ))

}
