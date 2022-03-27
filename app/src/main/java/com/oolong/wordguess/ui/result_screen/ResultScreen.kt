package com.oolong.wordguess.ui.result_screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.oolong.wordguess.WordGuessNavHost
import com.oolong.wordguess.ui.result_screen.components.StatisticsFrame

/*
* This screen will be a static true result screen.
* It may show streak, duration and how many try player gets.
* */
@Composable
fun ResultScreen(
    navController: NavController,
    result: String,
    countOfPlayerGames: Int?,
    winPercentage: Int?,
    currentStreak: Int?,
    maxStreak: Int?
) {
    var buttonText = ""
    var resultText = ""

    when(result) {
        "true" -> {
            resultText = "Congrats!"
            buttonText = "Go next!"
        }
        "false" -> {
            resultText = "Sad!"
            buttonText = "Try again!"
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StatisticsFrame(
            countOfPlayedGames = countOfPlayerGames,
            winPercentage = winPercentage,
            currentStreak = currentStreak,
            maxStreak = maxStreak
        )
        Text(
            text = resultText
        )
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                navController.navigate("game_screen")
            }
        ) {
            Text(
                text = buttonText
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewTrueScreen() {
    val navController = rememberNavController()
    val result = "true"
    ResultScreen(
        navController = navController,
        result = result,
        countOfPlayerGames = 1,
        winPercentage = 20,
        currentStreak = 3,
        maxStreak = 3
    )
}