package com.muhammadali.timer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TimerScreen( viewModel: TimerViewModel) {
    val time by viewModel.timer.collectAsState(initial = Time(0,0,0))
    val isCounting: Boolean by remember{viewModel.isCounting}

   Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF2F2F2F),
                            Color(0xFF242424)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {


            Text(
                modifier = Modifier
                    .clickable {
                        viewModel.apply {
                            if (isCounting) pause() else resume()
                        }

                        println("IsCounting bla")
                    },
                text = "${if(time.hour < 10) "0${time.hour}" else time.hour}" +
                        ":${if(time.min < 10) "0${time.min}" else time.min}" +
                                ":${if(time.sec < 10) "0${time.sec}" else time.sec}",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Timer(
                lineColor = Color.Red,
                size = 200.dp,
                time = time.sec,
                l2R = .04f
            )

            Timer(
                lineColor = Color.Green,
                size = 240.dp,
                time = time.min,
                l2R = .04f
            )

        }

        if (time != Time(0,0,0)){
            Button(onClick = {
                viewModel.resetTimer()
            },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent
                )
            ) {
                Text(text = "Reset Time", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimerScreenPreview() {
    TimerScreen(TimerViewModel())
}