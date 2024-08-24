package com.swaraj.algorythm.sorting

class BubbleSort :SortingAlgorithm
{

    override suspend fun sort(
        arr: IntArray,
        onSwap: (IntArray) -> Unit
    ) {

        val n = arr.size
        for (i in 0 until n - 1) {
            for (j in 0 until n - i - 1) {
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    val temp = arr[j]
                    arr[j] = arr[j + 1]
                    arr[j + 1] = temp
                    onSwap(arr)
                }
            }
        }
    }
}