package com.muhammadali.timer

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn

class TimerViewModel : ViewModel() {


    private var time = 0
    val isCounting = mutableStateOf(false)
    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    val timer = flow {
        while (true) {
            if (isCounting.value) {

                delay(1000L)
                time ++
                emit(fromSecToTime(time))
            }

            delay(10L)
        }
    }

    fun resetTimer() {
        time = 0
    }

    fun pause() {
        isCounting.value = false
    }

    fun resume() {
        isCounting.value = true
        //timer.launchIn(viewModelScope)
    }


    private fun fromSecToTime(time: Int): Time {
        val sec = time % 60
        val min = ((time / 60) % 60)
        val hours = time / 3600

        return Time(sec, min, hours)
    }
}