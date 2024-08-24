package com.swaraj.algorythm.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swaraj.algorythm.data.AlgorithmEvents
import com.swaraj.algorythm.sorting.SortingAlgorithm
import kotlinx.coroutines.launch

class AlgorithmViewModel(
    private val sortingAlgorithm: SortingAlgorithm
): ViewModel() {

    val onSortingFinish = mutableStateOf(false)

    val arr =
        mutableStateOf(intArrayOf(100, 120, 180, 155, 240, 205, 225, 120, 185, 223, 134, 164,192,250,260))

    val isPlaying = mutableStateOf(false)
    val onSortingFinished = mutableStateOf(false)
    private var delayy = 150L
    private var pause = false
    private var next = 1
    private var previous = 0

    private var sortedArrayLevels = mutableListOf<List<Int>>()

    init {
        viewModelScope.launch {
            sortingAlgorithm.sort(
                arr.value.clone()
            ) { modifiedArray ->
                sortedArrayLevels.add(modifiedArray.toMutableList())
            }
        }
    }

    fun onEvent(event: AlgorithmEvents) {
        when (event) {
            is AlgorithmEvents.PlayPause -> {
                playPauseAlgorithm()
            }
            is AlgorithmEvents.SlowDown -> {
                slowDown()
            }
            is AlgorithmEvents.SpeedUp -> {
                speedUp()
            }
            is AlgorithmEvents.Previous -> {
                previousState()
            }
            is AlgorithmEvents.Next -> {
                nextState()
            }
            /*is AlgorithmEvents.DeleteItem -> {
                deleteItemFromArray(event.index)
            }

            is AlgorithmEvents.UpdateItem -> {
                updateItemInTheArray(event.index, event.value)
            }*/
        }
    }

    private fun playPauseAlgorithm() {
        if (isPlaying.value) {
            pause()
        } else {
            play()
        }
        isPlaying.value = !isPlaying.value
    }

    private var sortingState = 0
    private fun play() = viewModelScope.launch {
        pause = false
        for (i in sortingState until sortedArrayLevels.size) {
            if (!pause) {
                kotlinx.coroutines.delay(delayy)
                arr.value = sortedArrayLevels[i].toIntArray()
            } else {
                sortingState = i
                next = i +1
                previous = i
                return@launch
            }
        }
        onSortingFinished.value = true
    }

    private fun pause() {
        pause = true
    }

    private fun nextState() {
        if (next < sortedArrayLevels.size) {
            arr.value = sortedArrayLevels[next++].toIntArray()
            next++
            previous++
        }
    }

    private fun previousState() {
        if (previous >= 0) {
            arr.value = sortedArrayLevels[previous--].toIntArray()
            next--
            previous--
        }
    }

    private fun speedUp() {
        if (delayy >=30L) {
            delayy -=10
        }
    }

    private fun slowDown() {

        delayy+=20

    }
}