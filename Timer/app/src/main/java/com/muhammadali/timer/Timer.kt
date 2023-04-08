package com.muhammadali.timer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin
import java.lang.Math.toRadians

/** a is the start point (the one with more destiny from the center)*/

private fun fromCoordinatesToOffset(a: Offset, b: Offset, centerOffset: Offset): Pair<Offset, Offset> {
    val aCanvas = Offset(absolute(a.x - centerOffset.x),
        absolute(a.y - centerOffset.y))

    val bCanvas = Offset(absolute(b.x - centerOffset.x),
        absolute(b.y - centerOffset.y))

    return Pair(aCanvas, bCanvas)
}

private fun absolute(value: Float) = if (value >= 0) value else -value

@Composable
fun Timer(
    lineColor: Color,
    size: Dp,
    time: Int,
    l1R: Float = 0.9f,
    l2R: Float = 0.5f
) {
    Canvas(modifier = Modifier.size(size)) {

        val l1 = ((size.toPx() / 2) * (l1R / (l1R + l2R)))
        val l2 = ((size.toPx() / 2) * (l2R / (l1R + l2R)))

        println("L1: ${l1R / (l1R + l2R)}  L2: ${(l2R / (l1R + l2R))}")

        var angle = 0f
        val angleIncreasingRate = 6f

        for (i in 0 until time) {
            angle = angleIncreasingRate * i

            val aCoordinates = Offset(
                x = (l1 + l2) * sin(toRadians(angle.toDouble()).toFloat()),
                y = (l1 + l2) * cos(toRadians(angle.toDouble()).toFloat())
            )

            val bCoordinates = Offset(
                x = l1 * sin(toRadians(angle.toDouble()).toFloat()),
                y = l1 * cos(toRadians(angle.toDouble()).toFloat())
            )

            val abLine = fromCoordinatesToOffset(
                aCoordinates,
                bCoordinates,
                this.center
            )

            drawLine(
                color = lineColor,
                start = abLine.second,
                end = abLine.first,
                strokeWidth = 6f,
                cap = StrokeCap.Round

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTimer() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Timer(
            lineColor = Color.Green,
            size = 200.dp,
            time = 45,
            l1R = 9f,
            l2R = 0.5f
        )

        Timer(
            lineColor = Color.Red,
            size = 250.dp,
            time = 5,
            l1R = 9f,
            l2R = 0.5f
        )
    }
}