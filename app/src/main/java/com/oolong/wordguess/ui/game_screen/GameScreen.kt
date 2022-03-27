package com.oolong.wordguess.ui.game_screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import com.oolong.wordguess.WordGuessNavHost
import com.oolong.wordguess.ui.game_screen.components.Board
import com.oolong.wordguess.ui.game_screen.components.EnglishKeyboard

@Composable
fun GameScreen(
    viewModel: GameScreenViewModel,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Board(
            answerList = viewModel.answerList
        )

        Text(
            text = viewModel.currentWord
        )

        EnglishKeyboard(
            stateMap = viewModel.keyboardButtonGameStateMap,
            onEnterPressed = {
                when (viewModel.onEnterButtonPress()){
                    ResultOnEnter.NOT_A_WORD ->{
                        Log.d("GameScreen", "Word list does not contain user word.")
                    }
                    ResultOnEnter.INCOMPLETE -> {
                        Log.d("GameScreen", "User word is incomplete.")
                    }
                    ResultOnEnter.END_OF_BOARD -> {
                        Log.d("GameScreen", "It's false answer!")
                        viewModel.clearGameBoard()
                        navController.navigate("result_screen/" + "false")
                    }
                    ResultOnEnter.TRUE -> {
                        Log.d("GameScreen", "It's true answer!")
                        viewModel.clearGameBoard()
                        navController.navigate("result_screen/" + "true/" + viewModel.countOfPlayedGames + "/" + viewModel.winPercentage + "/" + viewModel.currentStreak + "/" + viewModel.maxStreak
                        )
                        val s = "result_screen/" + "true/" + viewModel.countOfPlayedGames + "/" + viewModel.winPercentage + "/" + viewModel.currentStreak + "/" + viewModel.maxStreak
                        Log.d("GameScreen", s)
                    }
                }
            },
            onBackspacePressed = {
                viewModel.onBackspacePressed()
            }
        ) {
            viewModel.onCustomKeyboardButtonPress(it)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewGameScreen() {
    val viewModel = GameScreenViewModel()
    val navController = rememberNavController()
    GameScreen(viewModel = viewModel, navController = navController)
}