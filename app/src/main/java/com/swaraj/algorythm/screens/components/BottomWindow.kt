package com.swaraj.algorythm.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalTextToolbar
import androidx.compose.ui.platform.TextToolbar
import androidx.compose.ui.platform.TextToolbarStatus
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.dp

@Composable
fun BottomWindow(
    modifier: Modifier = Modifier,
    description: @Composable () -> Unit,
    code: @Composable () -> Unit,
    algoName: String // Add this parameter
) {
    val selectedTabIndex = remember { mutableStateOf(0) }
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    var showSnackbar by remember { mutableStateOf(false) }

    // State to hold the extracted code text
    var codeText by remember { mutableStateOf("") }

    // Function to get the code based on algoName
    fun getCodeForAlgorithm(name: String): String {
        return when (name) {
            "Bubble Sort" -> """
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
            "Insertion Sort" -> """
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
            "Merge Sort" -> """
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
            "Quick Sort" -> """
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
            else -> """
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
    }

    // Set the initial code text
    LaunchedEffect(algoName) {
        codeText = getCodeForAlgorithm(algoName)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex.value,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Tab(selected = selectedTabIndex.value == 0, onClick = { selectedTabIndex.value = 0 }) {
                Text("Description")
            }
            Tab(selected = selectedTabIndex.value == 1, onClick = { selectedTabIndex.value = 1 }) {
                Text("Code")
            }
        }
        Box(modifier = Modifier.fillMaxSize()) {
            when (selectedTabIndex.value) {
                0 -> description()
                1 -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        code()
                        IconButton(
                            onClick = {
                                clipboardManager.setText(AnnotatedString(codeText))
                                showSnackbar = true
                            },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentCopy,
                                contentDescription = "Copy code"
                            )
                        }
                    }
                }
            }
        }
    }
    if (showSnackbar) {
        Snackbar(
            modifier = Modifier.padding(16.dp),
            action = {
                TextButton(onClick = { showSnackbar = false }) {
                    Text("Dismiss")
                }
            }
        ) {
            Text("Code copied to clipboard")
        }
        LaunchedEffect(showSnackbar) {
            kotlinx.coroutines.delay(3000)
            showSnackbar = false
        }
    }
}

