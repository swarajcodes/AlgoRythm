package com.swaraj.algorhythm.visualiser

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
//import com.swaraj.algorhythm.data.AlgorithmEvents
//import com.swaraj.algorhythm.viewmodel.AlgorithmViewModel
import com.swaraj.algorythm.data.AlgorithmEvents
import com.swaraj.algorythm.viewmodel.AlgorithmViewModel

@Composable
fun Visualiser(viewModel: AlgorithmViewModel){
    Column {
        VisualiserSection(arr =
        viewModel.arr.value,
            modifier = Modifier.fillMaxWidth()
        )

        val isPlaying=viewModel.isPlaying.value
        val isFinished = viewModel.onSortingFinished.value

        /*VisBottomBar(
            playPauseClick = { viewModel.onEvent(AlgorithmEvents.PlayPause)},
            slowDownClick = { viewModel.onEvent(AlgorithmEvents.SlowDown) },
            speedUpClick = { viewModel.onEvent(AlgorithmEvents.SpeedUp) },
            previousClick = { viewModel.onEvent(AlgorithmEvents.Previous) },
            nextClick = { viewModel.onEvent(AlgorithmEvents.Next) })*/
    }
}