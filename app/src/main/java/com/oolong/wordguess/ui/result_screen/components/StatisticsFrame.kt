package com.oolong.wordguess.ui.result_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun StatisticsFrame(
    countOfPlayedGames: Int?,
    winPercentage: Int?,
    currentStreak: Int?,
    maxStreak: Int?
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "STATISTICS",
            fontSize = 24.sp
        )
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            GameCountLabel(countOfPlayedGames = countOfPlayedGames)
            WinPercentageLabel(winPercentage = winPercentage)
            CurrentStreakLabel(currentStreak = currentStreak)
            MaxStreakLabel(maxStreak = maxStreak)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewStatisticsFrame(){
    StatisticsFrame(countOfPlayedGames = 3, winPercentage = 30, currentStreak = 3, maxStreak = 3)
}