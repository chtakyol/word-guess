package com.oolong.wordguess.ui.game_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Board(){
    Column() {
        LazyRow(){
            for (i in 1..5) {
                item {
                    LetterBox(
                        modifier = Modifier,
                        borderColor = Color.Black,
                        backgroundColor = Color.White,
                        letter = "d",
                        letterColor = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun BoardPreview(){
    Board()
}