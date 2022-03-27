package com.oolong.wordguess.ui.result_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun WinPercentageLabel(
    winPercentage: Int
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = winPercentage.toString(),
            fontSize = 24.sp
        )
        Text(
            text = "Win %"
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewWinPercentageLabel(){
    WinPercentageLabel(winPercentage = 2)
}