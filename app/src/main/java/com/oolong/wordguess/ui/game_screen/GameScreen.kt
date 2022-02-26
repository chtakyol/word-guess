package com.oolong.wordguess.ui.game_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.oolong.wordguess.ui.game_screen.components.Board
import com.oolong.wordguess.ui.game_screen.components.EnglishKeyboard

@Composable
fun GameScreen(
    viewModel: GameScreenViewModel
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

        EnglishKeyboard(
            state = KeyboardButtonGameState.DEFAULT,
            onEnterPressed = {
                viewModel.onEnterButtonPress()
            },
            onBackspacePressed = {

            }
        ) {
            viewModel.onCustomKeyboardButtonPress(it)
        }
    }
}

@Composable
@Preview
fun PreviewGameScreen() {
    val viewModel = GameScreenViewModel()
    
    GameScreen(viewModel = viewModel)
}