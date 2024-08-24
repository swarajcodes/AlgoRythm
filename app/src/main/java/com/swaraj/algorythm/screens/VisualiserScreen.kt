package com.swaraj.algorythm.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.swaraj.algorhythm.visualiser.Visualiser
import com.swaraj.algorythm.data.AlgorithmEvents
import com.swaraj.algorythm.screens.components.BottomBar
import com.swaraj.algorythm.screens.components.HeadSection
//import com.swaraj.algorythm.screens.components.VisualizerSection
import com.swaraj.algorythm.viewmodel.AlgorithmViewModel
import com.swaraj.algorythm.screens.components.BottomWindow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisualiserScreen (
    navHostController: NavHostController,
    viewModel: AlgorithmViewModel,
    algoName: String,
    timeComp: String
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    Scaffold (
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            HeadSection(algoName = algoName, timeComp = timeComp) {
                navHostController.navigateUp()
            }
        },
        bottomBar = {
            BottomBar(
                modifier = Modifier.fillMaxWidth(),
                onPlayPauseClick = {
                    viewModel.onEvent(AlgorithmEvents.PlayPause)
                },

                onNextStepClick = {
                    viewModel.onEvent(AlgorithmEvents.Next)
                },
                onBackStepClick = {
                    viewModel.onEvent(AlgorithmEvents.Previous)
                },
                onSpeedUpClick = { viewModel.onEvent(AlgorithmEvents.SpeedUp) },
                onSlowDownClick = { viewModel.onEvent(AlgorithmEvents.SlowDown) }
                //isPlaying = shouldStartAlgorithm

            )
        }
    ){
            ContentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(ContentPadding)
                .padding(top = 18.dp, start = 18.dp, bottom = 0.dp, end = 24.dp)
                .verticalScroll(rememberScrollState())

        ) {
            Visualiser(viewModel)
            Spacer(modifier = Modifier.height(5.dp))
            /*Text(algoName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center)*/
            //Spacer(modifier = Modifier.height(5.dp))
            BottomWindow(
                description = {
                    Text(
                        text = when (algoName) {
                            "Bubble Sort" -> {
                                """
                                            Bubble Sort is a simple sorting algorithm.
                                            It repeatedly steps through the list,
                                            compares adjacent elements and swaps them
                                            if they are in the wrong order.
                                            The pass through the list is repeated
                                            until the list is sorted.
                                            It has a time complexity of O(n²).
                                            """.trimIndent()
                            }
                            "Insertion Sort" -> {
                                """
                                            Insertion Sort builds the final sorted array
                                            one item at a time. It is much less
                                            efficient on large lists than more
                                            advanced algorithms such as quicksort,
                                            heapsort, or merge sort. It works
                                            well for small arrays or partially sorted arrays.
                                            """.trimIndent()
                            }
                            "Merge Sort" -> {
                                """
                                            Merge Sort is an efficient, stable,
                                            comparison-based, divide and conquer sorting
                                            algorithm. Most implementations produce a
                                            stable sort, meaning that the order of
                                            equal elements is the same in the input
                                            and output. It has a time complexity of
                                            O(n log n).
                                            """.trimIndent()
                            }
                            "Quick Sort" -> {
                                """
                                            Quick Sort is an efficient sorting algorithm,
                                            serving as a systematic method for placing
                                            the elements of an array in order.
                                            Developed by Tony Hoare in 1959,
                                            it is still a commonly used algorithm.
                                            It has an average time complexity of O(n log n).
                                            """.trimIndent()
                            }
                            "Selection Sort" -> {
                                """
                                            Selection Sort is a simple sorting algorithm.
                                            The algorithm divides the input list into
                                            two parts: the sublist of items already
                                            sorted, which is built up from left to right
                                            at the front (left) of the list,
                                            and the sublist of items remaining to be sorted that occupy the rest of the list.
                                            """.trimIndent()
                            }
                            else -> {
                                "This is information about $algoName."
                            }
                        },
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start
                    )
                },
                code = {
                    Text(
                        when (algoName) {
                            "Bubble Sort" -> {
                                """
                                            for (i in 0 until n - 1) {
                                                for (j in 0 until n - i - 1) {
                                                    if (arr[j] > arr[j + 1]) {
                                                        // Swap arr[j] and arr[j+1]
                                                        val temp = arr[j]
                                                        arr[j] = arr[j + 1]
                                                        arr[j + 1] = temp
                                                    }
                                                }
                                            }
                                            """.trimIndent()
                            }
                            "Insertion Sort" -> {
                                """
                                            for (i in 1 until n) {
                                                val key = arr[i]
                                                var j = i - 1
                                                while (j >= 0 && arr[j] > key) {
                                                    arr[j + 1] = arr[j]
                                                    j = j - 1
                                                }
                                                arr[j + 1] = key
                                            }
                                            """.trimIndent()
                            }
                            "Merge Sort" -> {
                                """
                                            fun mergeSort(arr: IntArray, l: Int, r: Int) {
                                                if (l < r) {
                                                    val m = (l + r) / 2
                                                    mergeSort(arr, l, m)
                                                    mergeSort(arr, m + 1, r)
                                                    merge(arr, l, m, r)
                                                }
                                            }

                                            fun merge(arr: IntArray, l: Int, m: Int, r: Int) {
                                                val n1 = m - l + 1
                                                val n2 = r - m
                                                val L = IntArray(n1)
                                                val R = IntArray(n2)

                                                for (i in 0 until n1) L[i] = arr[l + i]
                                                for (j in 0 until n2) R[j] = arr[m + 1 + j]

                                                var i = 0
                                                var j = 0
                                                var k = l
                                                while (i < n1 && j < n2) {
                                                    if (L[i] <= R[j]) {
                                                        arr[k] = L[i]
                                                        i++
                                                    } else {
                                                        arr[k] = R[j]
                                                        j++
                                                    }
                                                    k++
                                                }

                                                while (i < n1) {
                                                    arr[k] = L[i]
                                                    i++
                                                    k++
                                                }

                                                while (j < n2) {
                                                    arr[k] = R[j]
                                                    j++
                                                    k++
                                                }
                                            }
                                            """.trimIndent()
                            }
                            "Quick Sort" -> {
                                """
                                            fun quickSort(arr: IntArray, low: Int, high: Int) {
                                                if (low < high) {
                                                    val pi = partition(arr, low, high)
                                                    quickSort(arr, low, pi - 1)
                                                    quickSort(arr, pi + 1, high)
                                                }
                                            }

                                            fun partition(arr: IntArray, low: Int, high: Int): Int {
                                                val pivot = arr[high]
                                                var i = (low - 1)
                                                for (j in low until high) {
                                                    if (arr[j] <= pivot) {
                                                        i++
                                                        val temp = arr[i]
                                                        arr[i] = arr[j]
                                                        arr[j] = temp
                                                    }
                                                }
                                                val temp = arr[i + 1]
                                                arr[i + 1] = arr[high]
                                                arr[high] = temp
                                                return i + 1
                                            }
                                            """.trimIndent()
                            }
                            else -> {
                                """
                                            for (i in 0 until n - 1) {
                                                var minIdx = i
                                                for (j in i + 1 until n) {
                                                    if (arr[j] < arr[minIdx]) {
                                                        minIdx = j
                                                    }
                                                }
                                                val temp = arr[minIdx]
                                                arr[minIdx] = arr[i]
                                                arr[i] = temp
                                            }
                                            """.trimIndent()
                            }
                        },
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                },
                algoName = algoName
            )
        }
    }
}