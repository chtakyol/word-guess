package com.oolong.wordguess.ui.game_screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.oolong.wordguess.englishWordsList
import com.oolong.wordguess.ui.Answer

enum class KeyboardButtonGameState{
    WRONG, INCLUDED, RIGHT, DEFAULT
}

enum class BoxState {
    WAITING, ENTERED, EVALUATED_INCLUDED, EVALUATED_NOT_INCLUDED, EVALUATED_CORRECT
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

    private fun compareUserWord(playerWord: String): Boolean {
        Log.d("GameScreen", "Player word is $playerWord.")
        val tempPlayerWord = playerWord
        for (playerLetter in tempPlayerWord){
            if (currentWord.contains(playerLetter)){
                // EVALUATED_CORRECT or EVALUATED_INCLUDED
                if (tempPlayerWord.indexOf(playerLetter) == currentWord.indexOf(playerLetter)){
                    // EVALUATED_CORRECT
                    val indexToUpdate = getCurrentIndex(playerWord.indexOf(playerLetter))
//                    Log.d("GameScreen", "The index of ${playerWord.indexOf(playerLetter).toString()} is true but $indexToUpdate")
                    updateAnswerListsBoxState(indexToUpdate, BoxState.EVALUATED_CORRECT)
                } else {
                    // EVALUATED_INCLUDED
                    val indexToUpdate = getCurrentIndex(playerWord.indexOf(playerLetter))
//                    Log.d("GameScreen", "The index of ${playerWord.indexOf(playerLetter).toString()} is included $indexToUpdate")
                    updateAnswerListsBoxState(indexToUpdate, BoxState.EVALUATED_INCLUDED)
                }
            } else {
                // EVALUATED_NOT_INCLUDED
                val indexToUpdate = getCurrentIndex(playerWord.indexOf(playerLetter))
                updateAnswerListsBoxState(indexToUpdate, BoxState.EVALUATED_NOT_INCLUDED)
            }
        }

        if (playerWord.equals(currentWord, true)) {
            // score increase
            Log.d("GameScreen", "TRUE!")
            return true
        }
        Log.d("GameScreen", "FALSE!")
        return false
    }

    private fun getCurrentIndex(scanColumnIndex: Int): Int {
        return rowIndex.value * 5 + scanColumnIndex
    }

    private fun updateAnswerListsBoxState(index: Int, newState: BoxState){
        val tempAnswer = answerList[index]
        answerList.removeAt(index)
        tempAnswer.state = newState
        answerList.add(index, tempAnswer)
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
                compareUserWord(playerWord = playerWord) //This method depends on rowIndex, so call before assign.
                rowIndex.value++
                columnIndex.value = 0
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