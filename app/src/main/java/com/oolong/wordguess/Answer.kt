package com.oolong.wordguess.ui

enum class BoxState {
    WAITING, ENTERED, EVALUATED_INCLUDED, EVALUATED_CORRECT
}


/*
* Answer class is holds the guessed letter and letter boxes state properties.
* */
class Answer(
    var letter: String = "a",
    var state: BoxState
) {
}