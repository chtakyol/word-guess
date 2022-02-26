package com.oolong.wordguess.ui.game_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oolong.wordguess.ui.Answer
import com.oolong.wordguess.ui.game_screen.BoxState
import com.oolong.wordguess.ui.game_screen.KeyboardButtonGameState

/*
* This composable draw the box and letter for game board.
*
* TODO Change border color, fill color according to answers [BoxState].
* */
@Composable
fun LetterBox(
    modifier: Modifier,
    answer: Answer,
){
    val backgroundColor: Color = when (answer.state){
        BoxState.WAITING -> {
            Color(0xFFFFFFFF)
        }
        BoxState.ENTERED -> {
            Color(0xFFFFFFFF)
        }
        BoxState.EVALUATED_CORRECT -> {
            Color(0xFF6AAA64)
        }
        BoxState.EVALUATED_INCLUDED -> {
            Color(0xFFC9B458)
        }
        BoxState.EVALUATED_NOT_INCLUDED -> {
            Color(0xFF787C7E)
        }
    }
    val borderColor: Color = when (answer.state){
        BoxState.WAITING -> {
            Color(0xFFD3D6DA)
        }
        BoxState.ENTERED -> {
            Color(0xFF96999A)
        }
        BoxState.EVALUATED_CORRECT -> {
            Color(0xFF6AAA64)
        }
        BoxState.EVALUATED_INCLUDED -> {
            Color(0xFFC9B458)
        }
        BoxState.EVALUATED_NOT_INCLUDED -> {
            Color(0xFF787C7E)
        }
    }
    val letterColor: Color = when (answer.state){
        BoxState.WAITING -> {
            Color(0xFF000000)
        }
        BoxState.ENTERED -> {
            Color(0xFF000000)
        }
        BoxState.EVALUATED_CORRECT -> {
            Color(0xFFFFFFFF)
        }
        BoxState.EVALUATED_INCLUDED -> {
            Color(0xFFFFFFFF)
        }
        BoxState.EVALUATED_NOT_INCLUDED -> {
            Color(0xFFFFFFFF)
        }
    }
    Box(
        modifier = modifier
            .size(54.dp, 64.dp)
            .border(BorderStroke(2.dp, borderColor))
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = answer.letter.uppercase(),
            color = letterColor
        )
    }
}

@Composable
@Preview()
fun PreviewWaitingLetterBox(){
    LetterBox(
        modifier = Modifier,
        answer = Answer("", BoxState.WAITING)
    )
}

@Composable
@Preview()
fun PreviewEnteredLetterBox(){
    LetterBox(
        modifier = Modifier,
        answer = Answer("s", BoxState.ENTERED)
    )
}

@Composable
@Preview()
fun PreviewCorrectLetterBox(){
    LetterBox(
        modifier = Modifier,
        answer = Answer("s", BoxState.EVALUATED_CORRECT)
    )
}

@Composable
@Preview()
fun PreviewIncludedLetterBox(){
    LetterBox(
        modifier = Modifier,
        answer = Answer("s", BoxState.EVALUATED_INCLUDED)
    )
}

@Composable
@Preview()
fun PreviewNotIncludedLetterBox(){
    LetterBox(
        modifier = Modifier,
        answer = Answer("s", BoxState.EVALUATED_NOT_INCLUDED)
    )
}