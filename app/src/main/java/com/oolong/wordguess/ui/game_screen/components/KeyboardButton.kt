package com.oolong.wordguess.ui.game_screen.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.ButtonDefaults.textButtonColors
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oolong.wordguess.ui.game_screen.KeyboardButtonGameState

/*
* This components is the single button for game keyboard component.
* */
@Composable
fun KeyboardButton(
    modifier:Modifier = Modifier,
    letter: String,
    state: KeyboardButtonGameState,
    onClick: () -> Unit
){
    val backgroundColor: Color = when (state){
        KeyboardButtonGameState.INCLUDED -> {
            Color(0xFFC9B458)
        }
        KeyboardButtonGameState.RIGHT -> {
            Color(0xFF6AAA64)
        }
        KeyboardButtonGameState.WRONG -> {
            Color(0xFF787C7E)
        }
        KeyboardButtonGameState.DEFAULT -> {
            Color(0xFFD3D6DA)
        }
    }
    val textColor: Color = when (state){
        KeyboardButtonGameState.INCLUDED -> {
            Color(0xFFFFFFFF)
        }
        KeyboardButtonGameState.RIGHT -> {
            Color(0xFFFFFFFF)
        }
        KeyboardButtonGameState.WRONG -> {
            Color(0xFFFFFFFF)
        }
        KeyboardButtonGameState.DEFAULT -> {
            Color(0xFF000000)
        }
    }

    val buttonColors = textButtonColors(
        backgroundColor = backgroundColor,
    )
    TextButton(
        modifier = modifier
            .size(width = 32.dp, height = 64.dp),
        colors = buttonColors,
        onClick = {
            onClick()
        }
    ) {
        Text(
            text = letter.uppercase(),
            color = textColor
        )
    }
}

@Composable
@Preview
fun PreviewKeyboardButtonIncluded(){
    KeyboardButton(letter = "s", state = KeyboardButtonGameState.INCLUDED){}
}

@Composable
@Preview
fun PreviewKeyboardButtonWrong(){
    KeyboardButton(letter = "s", state = KeyboardButtonGameState.WRONG){}
}

@Composable
@Preview
fun PreviewKeyboardButtonDefault(){
    KeyboardButton(letter = "s", state = KeyboardButtonGameState.DEFAULT){}
}

@Composable
@Preview
fun PreviewKeyboardButtonRight(){
    KeyboardButton(letter = "s", state = KeyboardButtonGameState.RIGHT){}
}