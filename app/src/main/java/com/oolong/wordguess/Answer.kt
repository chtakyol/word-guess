package com.oolong.wordguess.ui

import com.oolong.wordguess.ui.game_screen.BoxState

/*
* Answer class is holds the guessed letter and letter boxes state properties.
* */
class Answer(
    var letter: String = "",
    var state: BoxState = BoxState.WAITING
) {
}