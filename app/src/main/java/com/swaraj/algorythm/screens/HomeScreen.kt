package com.swaraj.algorythm.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBarDefaults.containerColor
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.swaraj.algorythm.R
import java.lang.reflect.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Sorting", "Searching", "Data Structures")
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "AlgoRhythm",
                        color = Color.Black,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.purple_200)
                ),
                modifier = androidx.compose.ui.Modifier.fillMaxWidth()
            )
        },
        containerColor = colorResource(id = R.color.white),
        /*bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = { /*TODO*/ },
                        icon = { Icons.Filled.DateRange },
                        label = { Text("Sorting") }
                    )
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = { /*TODO*/ },
                        icon = { Icons.Filled.Search },
                        label = { Text("Searching") }
                    )
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = { /*TODO*/ },
                        icon = { Icons.Filled.Star },
                        label = { Text("Data Structures") }
                    )
                }
            }
        }*/
    ) { innerPadding ->
        Column(
            modifier = androidx.compose.ui.Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = androidx.compose.ui.Modifier.padding(16.dp))
            SortingTiles(navController = navController, algoName = "Bubble Sort", screenName = "BubbleSort", timeComp = "")
            SortingTiles(navController = navController, algoName = "Insertion Sort", screenName = "InsertionSort", timeComp = "")
            SortingTiles(navController = navController, algoName = "Merge Sort", screenName = "MergeSort", timeComp = "")
            SortingTiles(navController = navController, algoName = "Quick Sort", screenName = "QuickSort", timeComp = "")
            SortingTiles(navController = navController, algoName = "Selection Sort", screenName = "SelectionSort", timeComp = "")
        }
    }
}

@Composable
fun SortingTiles(
    navController: NavController,
    algoName: String,
    screenName: String,
    timeComp: String,
    //image: String
) {
    val context = LocalContext.current
    /*val imageResId = remember(image) {
        context.resources.getIdentifier(image, "drawable", context.packageName)*/

    Surface (
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clickable { navController.navigate(screenName) }
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 8.dp
    ){
        Row(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = algoName,
                fontSize = 24.sp,  // Increased font size
                fontWeight = FontWeight.Bold,
                lineHeight = 38.sp
            )
        }
    }
}