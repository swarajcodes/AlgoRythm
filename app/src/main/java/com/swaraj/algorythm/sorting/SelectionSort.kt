package com.swaraj.algorythm.sorting

class SelectionSort : SortingAlgorithm {

    override suspend fun sort(
        arr: IntArray,
        onSwap: (IntArray) -> Unit
    ) {
        val n = arr.size
        for (i in 0 until n - 1) {
            var minIdx = i
            for (j in i + 1 until n) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j
                }
            }
            if (minIdx != i) {
                // Swap arr[i] and arr[minIdx]
                val temp = arr[i]
                arr[i] = arr[minIdx]
                arr[minIdx] = temp
                onSwap(arr)
            }
        }
    }
}
