package com.swaraj.algorythm.sorting

class InsertionSort : SortingAlgorithm {

    override suspend fun sort(
        arr: IntArray,
        onSwap: (IntArray) -> Unit
    ) {
        val n = arr.size
        for (i in 1 until n) {
            val key = arr[i]
            var j = i - 1

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j]
                j -= 1
                onSwap(arr)
            }
            arr[j + 1] = key
            onSwap(arr)
        }
    }
}
