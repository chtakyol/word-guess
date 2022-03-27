package com.oolong.wordguess

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.oolong.wordguess.ui.game_screen.GameScreen
import com.oolong.wordguess.ui.game_screen.GameScreenViewModel
import com.oolong.wordguess.ui.result_screen.ResultScreen

@Composable
fun WordGuessNavHost(
    viewModel: GameScreenViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "game_screen") {
        composable("game_screen") {
            GameScreen(viewModel = viewModel, navController = navController)
        }
        composable(
            route = "result_screen/{result}/{countOfPlayedGames}/{winPercentage}/{currentStreak}/{maxStreak}",
            arguments = listOf(
                navArgument("result"){
                    type = NavType.StringType
                },
                navArgument("countOfPlayedGames"){
                    type = NavType.IntType
                },
                navArgument("winPercentage"){
                    type = NavType.IntType
                },
                navArgument("currentStreak"){
                    type = NavType.IntType
                },
                navArgument("maxStreak"){
                    type = NavType.IntType
                }
            )
        ) {
            ResultScreen(
                navController = navController,
                result = it.arguments?.getString("result").toString(),
                countOfPlayerGames = it.arguments?.getInt("countOfPlayedGames"),
                winPercentage = it.arguments?.getInt("winPercentage"),
                currentStreak = it.arguments?.getInt("currentStreak"),
                maxStreak = it.arguments?.getInt("maxStreak")
            )
        }
    }
}