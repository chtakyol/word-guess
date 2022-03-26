package com.oolong.wordguess.ui.game_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateMap
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
    stateMap: SnapshotStateMap<String, KeyboardButtonGameState>,
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
                                state = KeyboardButtonGameState.DEFAULT,
                                onClick = onEnterPressed
                            )
                        }
                        "remove" -> {
                            RemoveKeyboardButton(
                                state = KeyboardButtonGameState.DEFAULT,
                                onClick = onBackspacePressed
                            )
                        }
                        else -> {
                            KeyboardButton(
                                letter = letter,
                                state = if (stateMap.containsKey(letter)) stateMap[letter] as KeyboardButtonGameState  else KeyboardButtonGameState.DEFAULT,
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
    val keyboardButtonGameStateMap = remember{mutableStateMapOf("a" to KeyboardButtonGameState.RIGHT)}
    EnglishKeyboard(
        stateMap = keyboardButtonGameStateMap,
        onEnterPressed = {},
        onBackspacePressed = {}
    ){}
}