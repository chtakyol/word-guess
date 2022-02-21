package com.oolong.wordguess.ui.game_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/*
* This composable draw the box and letter for game board.
* */
@Composable
fun LetterBox(
    modifier: Modifier,
    borderColor: Color,
    backgroundColor: Color,
    letter: String,
    letterColor: Color
){
    Box(
        modifier = modifier
            .size(54.dp, 64.dp)
            .border(BorderStroke(2.dp, borderColor))
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = letter.uppercase(),
            color = letterColor
        )
    }
    
}

@Composable
@Preview()
fun LetterBoxPreview(){
    LetterBox(
        modifier = Modifier,
        borderColor = Color.Black,
        backgroundColor = Color.White,
        letter = "d",
        letterColor = Color.Black
    )
}