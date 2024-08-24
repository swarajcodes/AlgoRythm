package com.swaraj.algorythm.sorting

interface SortingAlgorithm {
    suspend fun sort(array: IntArray, onSwap: (IntArray) -> Unit)
}