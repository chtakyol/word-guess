package com.oolong.wordguess.ui.result_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun MaxStreakLabel(
    maxStreak: Int
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = maxStreak.toString(),
            fontSize = 24.sp
        )
        Text(
            text = "Max\nStreak"
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewMaxStreakLabel(){
    MaxStreakLabel(maxStreak = 3)
}