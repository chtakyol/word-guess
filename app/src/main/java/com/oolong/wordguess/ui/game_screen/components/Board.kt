package com.oolong.wordguess.ui.game_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oolong.wordguess.ui.Answer
import com.oolong.wordguess.ui.BoxState

/*
* Board holds the guesses.
*/
@Composable
fun Board(
    answerList: List<List<Answer>>
){
    Column(
    ) {
        for (i in 0..5) {
            LazyRow(
                modifier = Modifier
            ){
                for (j in 0..4) {
                    item {
                        LetterBox(
                            modifier = Modifier
                                .padding(2.dp),
                            borderColor = Color.Black,
                            backgroundColor = Color.White,
                            answer = answerList[i][j],
                            letterColor = Color.Black
                        )
                    }
                }
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun BoardPreview(){
    val l = listOf<List<Answer>>(
        listOf(
            Answer("a", BoxState.WAITING),
            Answer("a", BoxState.WAITING),
            Answer("a", BoxState.WAITING),
            Answer("a", BoxState.WAITING),
            Answer("a", BoxState.WAITING),
            ),
        listOf(
            Answer("n", BoxState.WAITING),
            Answer("n", BoxState.WAITING),
            Answer("n", BoxState.WAITING),
            Answer("n", BoxState.WAITING),
            Answer("n", BoxState.WAITING),
        ),
        listOf(
            Answer("a", BoxState.WAITING),
            Answer("a", BoxState.WAITING),
            Answer("a", BoxState.WAITING),
            Answer("a", BoxState.WAITING),
            Answer("a", BoxState.WAITING),
        ),
        listOf(
            Answer("a", BoxState.WAITING),
            Answer("a", BoxState.WAITING),
            Answer("a", BoxState.WAITING),
            Answer("a", BoxState.WAITING),
            Answer("a", BoxState.WAITING),
        ),
        listOf(
            Answer("a", BoxState.WAITING),
            Answer("a", BoxState.WAITING),
            Answer("a", BoxState.WAITING),
            Answer("a", BoxState.WAITING),
            Answer("a", BoxState.WAITING),
        ),
        listOf(
            Answer("a", BoxState.WAITING),
            Answer("a", BoxState.WAITING),
            Answer("a", BoxState.WAITING),
            Answer("a", BoxState.WAITING),
            Answer("a", BoxState.WAITING),
        )
    )


    Board(answerList = l)
}