package com.oolong.wordguess.ui.game_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oolong.wordguess.ui.Answer
import com.oolong.wordguess.ui.game_screen.GameScreenViewModel

/*
* Board holds the guesses.
*/
@Composable
fun Board(
    answerList: SnapshotStateList<Answer>
){
    Column(
    ) {
        for (i in 0..5) {
            LazyRow(
                modifier = Modifier
            ){
                for (j in 0..4) {
                    val answer = try {
                        answerList[j + 5*i]
                    } catch (e: IndexOutOfBoundsException){
                        Answer()
                    }
                    item {
                        LetterBox(
                            modifier = Modifier
                                .padding(2.dp),
                            answer = answer
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
    val viewModel = GameScreenViewModel()
    Board(answerList = viewModel.answerList)
}