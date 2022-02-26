package com.oolong.wordguess.ui.game_screen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.oolong.wordguess.englishWordsList
import com.oolong.wordguess.ui.Answer

enum class KeyboardButtonGameState{
    WRONG, INCLUDED, RIGHT, DEFAULT
}

enum class BoxState {
    WAITING, ENTERED, EVALUATED_INCLUDED, EVALUATED_CORRECT
}

class GameScreenViewModel : ViewModel() {
    lateinit var currentWord: String

    private var rowIndex = mutableStateOf(0)
    private var columnIndex = mutableStateOf(0)

    var answerList = mutableStateListOf<Answer>()

    private fun getNextWord(){
        currentWord = englishWordsList.random()
    }

    init {
        getNextWord()
    }

    private fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(currentWord, true)) {
            // score increase
            return true
        }
        return false
    }

    fun onCustomKeyboardButtonClick(letter: String){
        answerList.add(Answer(letter, BoxState.WAITING))
        columnIndex.value++
        debugBoardList()
    }

    private fun debugBoardList(){
        for (ans in answerList){
            Log.d("GameScreen", ans.letter)
        }
    }
}