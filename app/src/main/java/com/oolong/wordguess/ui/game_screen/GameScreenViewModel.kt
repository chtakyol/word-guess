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
    lateinit var playerWord: String

    private var maxRowIndex = 6
    private var maxColumnIndex = 4

    private var rowIndex = mutableStateOf(0)
    private var columnIndex = mutableStateOf(0)

    var answerList = mutableStateListOf<Answer>()

    private fun getNextWord(){
        currentWord = englishWordsList.random()
        Log.d("GameScreen", "Current word is $currentWord.")
    }

    init {
        getNextWord()
    }

    private fun isUserWordCorrect(playerWord: String): Boolean {
        Log.d("GameScreen", "Player word is $playerWord.")
        if (playerWord.equals(currentWord, true)) {
            // score increase
            Log.d("GameScreen", "TRUE!")
            return true
        }
        Log.d("GameScreen", "FALSE!")
        return false
    }

    fun onCustomKeyboardButtonPress(letter: String){
        if (columnIndex.value <= maxColumnIndex){
            answerList.add(Answer(letter, BoxState.WAITING))
            columnIndex.value++
        }
    }

    fun onEnterButtonPress(){
        if (rowIndex.value < maxRowIndex){
            if (columnIndex.value % maxColumnIndex == 1){
                playerWord = constructWord() // This method depends on rowIndex value, so call before assign.
                rowIndex.value++
                columnIndex.value = 0
                isUserWordCorrect(playerWord = playerWord)
            } else {
                // We can show "Pls finish your word".
            }
        } else {
            // Player will lost
        }
    }

    private fun constructWord(): String{
        val start = 5 * rowIndex.value
        val end = 5 * rowIndex.value + maxColumnIndex
        val tempList = mutableListOf<String>()
        for(answer in answerList.slice(start..end)){
            tempList.add(answer.letter)
        }
        return tempList.joinToString("")

    }

    private fun debugBoardList(){
        for (ans in answerList){
            Log.d("GameScreen", ans.letter)
        }
    }
}