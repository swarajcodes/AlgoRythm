package com.swaraj.algorythm.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.swaraj.algorythm.data.AlgorithmViewModelProvider
import com.swaraj.algorythm.screens.HomeScreen
import com.swaraj.algorythm.screens.VisualiserScreen
import com.swaraj.algorythm.sorting.BubbleSort
import com.swaraj.algorythm.sorting.InsertionSort
import com.swaraj.algorythm.sorting.MergeSort
import com.swaraj.algorythm.sorting.QuickSort
import com.swaraj.algorythm.sorting.SelectionSort
import com.swaraj.algorythm.viewmodel.AlgorithmViewModel

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    
    NavHost(navController = navController, startDestination = "HomeScreen") {
        composable("HomeScreen") {
            HomeScreen(navController)
        }
        composable("BubbleSort") {
            val viewModel: AlgorithmViewModel = viewModel(
                factory =AlgorithmViewModelProvider(BubbleSort())
            )
            VisualiserScreen(navHostController = navController, viewModel = viewModel, algoName = "Bubble Sort", timeComp = "n\u00B2")
        }
        composable("InsertionSort") {
            val viewModel: AlgorithmViewModel = viewModel(
                factory = AlgorithmViewModelProvider(InsertionSort())
            )
            VisualiserScreen(navHostController = navController, viewModel = viewModel, algoName = "Insertion Sort", timeComp = "n\u00B2" )
        }
        composable("QuickSort") {
            val viewModel: AlgorithmViewModel = viewModel(
                factory = AlgorithmViewModelProvider(QuickSort())
            )
            VisualiserScreen(navHostController = navController, viewModel = viewModel, algoName = "Quick Sort", timeComp = "nlogn" )
        }
        composable("MergeSort") {
            val viewModel: AlgorithmViewModel = viewModel(
                factory = AlgorithmViewModelProvider(MergeSort())
            )
            VisualiserScreen(navHostController = navController, viewModel = viewModel, algoName = "Merge Sort", timeComp = "nlogn" )
        }
        composable("SelectionSort") {
            val viewModel: AlgorithmViewModel = viewModel(
                factory = AlgorithmViewModelProvider(SelectionSort())
            )
            VisualiserScreen(navHostController = navController, viewModel = viewModel, algoName = "Selection Sort", timeComp = "n\u00B2" )
        }
    }
}