package com.oolong.wordguess.ui.result_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/*
* This screen will be a static true result screen.
* It may show streak, duration and how many try player gets.
* */
@Composable
fun ResultScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Congrats!"
        )
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = "Go next!"
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewTrueScreen() {
    ResultScreen()
}