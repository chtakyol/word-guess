package com.oolong.wordguess.ui.game_screen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oolong.wordguess.ui.game_screen.KeyboardButtonGameState


@Composable
fun EnterKeyboardButton(
    state: KeyboardButtonGameState,
    onClick: () -> Unit
){
    KeyboardButton(
        modifier = Modifier.size(width = 68.dp, height = 64.dp),
        letter = "Enter",
        state = state,
        onClick = onClick
    )
}

@Composable
@Preview
fun PreviewEnterKeyboardButton(){
    EnterKeyboardButton(
        state =  KeyboardButtonGameState.DEFAULT
    ){

    }
}