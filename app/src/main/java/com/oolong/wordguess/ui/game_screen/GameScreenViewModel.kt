package com.oolong.wordguess.ui.game_screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
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

enum class ResultOnEnter {
    END_OF_BOARD, TRUE, INCOMPLETE, NOT_A_WORD
}

class CompareObj (
    val index: Int,
    val first: Char,
    var second: BoxState
    )

class GameScreenViewModel : ViewModel() {
    lateinit var currentWord: String
    lateinit var playerWord: String

    private var maxRowIndex = 6
    private var maxColumnIndex = 4

    private var rowIndex = mutableStateOf(0)
    private var columnIndex = mutableStateOf(0)

    var resultOnEnter = mutableStateOf(ResultOnEnter.INCOMPLETE)

    var answerList = mutableStateListOf<Answer>()

    var keyboardButtonGameStateMap = mutableStateMapOf<String, KeyboardButtonGameState>()

    // Score related variables
    var countOfPlayedGames = 12
    var winPercentage = 3
    var currentStreak = 4
    var maxStreak = 2

    private fun getNextWord(){
        currentWord = englishWordsList.random()
        Log.d("GameScreen", "Current word is $currentWord.")
    }

    init {
        getNextWord()
    }

    private fun addKeyboardButtonGameState(playerWord: String){
        for (i in 0..4) {
            if (playerWord[i] in currentWord && keyboardButtonGameStateMap[playerWord[i].toString()] != KeyboardButtonGameState.RIGHT){
                keyboardButtonGameStateMap[playerWord[i].toString()] = KeyboardButtonGameState.INCLUDED
            } else {
                keyboardButtonGameStateMap[playerWord[i].toString()] = KeyboardButtonGameState.WRONG
            }
            if (playerWord[i] == currentWord[i]){
                keyboardButtonGameStateMap[playerWord[i].toString()] = KeyboardButtonGameState.RIGHT
            }
        }
    }

    fun clearGameBoard(){
        resultOnEnter.value = ResultOnEnter.INCOMPLETE
        playerWord = ""
        rowIndex.value = 0
        columnIndex.value = 0
        answerList.clear()
        keyboardButtonGameStateMap.clear()
    }

    private fun compareUserWord(playerWord: String): Boolean {
        Log.d("GameScreen", "Player word is $playerWord.")
        val boxStateList = mutableListOf(
            BoxState.WAITING, BoxState.WAITING, BoxState.WAITING,
            BoxState.WAITING, BoxState.WAITING
        )
        val currentWordCompareList = mutableListOf(
            CompareObj(0, currentWord[0], BoxState.WAITING),
            CompareObj(1, currentWord[1], BoxState.WAITING),
            CompareObj(2, currentWord[2], BoxState.WAITING),
            CompareObj(3, currentWord[3], BoxState.WAITING),
            CompareObj(4, currentWord[4], BoxState.WAITING),
        )

        val playerWordCompareList = mutableListOf(
            CompareObj(0, playerWord[0], BoxState.WAITING),
            CompareObj(1, playerWord[1], BoxState.WAITING),
            CompareObj(2, playerWord[2], BoxState.WAITING),
            CompareObj(3, playerWord[3], BoxState.WAITING),
            CompareObj(4, playerWord[4], BoxState.WAITING),
        )

        // For correct letters
        for (i in 0..4) {
            for (item in currentWordCompareList) {
                if (playerWord[i] == item.first && i == item.index) {
                    boxStateList[i] = BoxState.EVALUATED_CORRECT
                    playerWordCompareList[i].second = BoxState.EVALUATED_CORRECT
                    currentWordCompareList[i].second = BoxState.EVALUATED_CORRECT
                    item.second = BoxState.EVALUATED_CORRECT
                }
            }
            // For included letters
            for (item in currentWordCompareList){
                if (
                    playerWordCompareList[i].second == BoxState.WAITING
                    && playerWord[i] == item.first
                    && item.second == BoxState.WAITING
                ) {
                    boxStateList[i] = BoxState.EVALUATED_INCLUDED
                    playerWordCompareList[i].second = BoxState.EVALUATED_INCLUDED
                    item.second = BoxState.EVALUATED_INCLUDED
                }
            }
            // For wrong letters
            for (item in currentWordCompareList){
                if (
                    playerWordCompareList[i].second == BoxState.WAITING
                    && playerWord[i] != item.first
                    && item.second == BoxState.WAITING
                ) {
                    boxStateList[i] = BoxState.EVALUATED_NOT_INCLUDED
                    playerWordCompareList[i].second = BoxState.EVALUATED_NOT_INCLUDED
                    item.second = BoxState.EVALUATED_NOT_INCLUDED
                }
            }
        }
        // Paint the board
        for (i in 0..4) {
            updateAnswerListsBoxState(
                getCurrentIndex(i),
                boxStateList[i]
            )
        }

        if (playerWord.equals(currentWord, true)) {
            // score increase
            return true
        }
        return false
    }

    private fun makeWordFromList(list: MutableList<CompareObj>): String {
        var word = ""
        for (item in list) {
            word += item.first
        }
        return word
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

    fun onEnterButtonPress() : ResultOnEnter{
        if (rowIndex.value < maxRowIndex - 1){
            if (columnIndex.value % maxColumnIndex == 1){
                playerWord = constructPlayerWord() // This method depends on rowIndex value, so call before assign.
                if (englishWordsList.contains(playerWord)) {
                    if (compareUserWord(playerWord = playerWord)) { //This method depends on rowIndex, so call before assign.
                        resultOnEnter.value = ResultOnEnter.TRUE
                        getNextWord()
                    }
                    rowIndex.value++
                    columnIndex.value = 0
                } else {
                    // Word list does not contain user word.
                    resultOnEnter.value = ResultOnEnter.NOT_A_WORD
                }
            } else {
                // User word is incomplete.
                resultOnEnter.value = ResultOnEnter.INCOMPLETE
            }
        } else {
            // User word is false and end of board.
            resultOnEnter.value = ResultOnEnter.END_OF_BOARD
            getNextWord()
        }
        addKeyboardButtonGameState(playerWord)
        return resultOnEnter.value
    }

    fun onBackspacePressed() {
        if (columnIndex.value > 0){
            answerList.removeLast()
            columnIndex.value--
        }
    }

    private fun constructPlayerWord(): String{
        val start = 5 * rowIndex.value
        val end = 5 * rowIndex.value + maxColumnIndex
        val tempList = mutableListOf<String>()
        for(answer in answerList.slice(start..end)){
            tempList.add(answer.letter)
        }
        return tempList.joinToString("")
    }
}