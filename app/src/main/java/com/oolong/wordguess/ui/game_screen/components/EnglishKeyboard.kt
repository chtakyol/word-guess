package com.oolong.wordguess.ui.game_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oolong.wordguess.ui.game_screen.KeyboardButtonGameState

/*
* The custom keyboard that user will interact.
* */
@Composable
fun EnglishKeyboard(
    state: KeyboardButtonGameState,
    onEnterPressed: () -> Unit,
    onBackspacePressed: () -> Unit,
    onClick: (String) -> Unit
){
    val letters = arrayOf(
        arrayOf("q", "w", "e", "r", "t", "y", "u", "i", "o", "p"),
        arrayOf("a", "s", "d", "f", "g", "h", "j", "k", "l"),
        arrayOf("enter", "z", "x", "c", "v", "b", "n", "m", "remove")
    )
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (row in letters){
            Row(
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth(.96f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (letter in row){
                    when (letter) {
                        "enter" -> {
                            EnterKeyboardButton(
                                state = state,
                                onClick = onEnterPressed
                            )
                        }
                        "remove" -> {
                            RemoveKeyboardButton(
                                state = state,
                                onClick = onBackspacePressed
                            )
                        }
                        else -> {
                            KeyboardButton(
                                letter = letter,
                                state = state,
                            ){
                                onClick(letter)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewKeyboard(){
    EnglishKeyboard(
        state = KeyboardButtonGameState.DEFAULT,
        onEnterPressed = {},
        onBackspacePressed = {}
    ){}
}