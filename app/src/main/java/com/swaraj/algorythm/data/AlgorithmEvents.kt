package com.swaraj.algorythm.data

sealed class AlgorithmEvents {
    object SlowDown : AlgorithmEvents()
    object SpeedUp : AlgorithmEvents()
    object PlayPause : AlgorithmEvents()
    object Previous : AlgorithmEvents()
    object Next : AlgorithmEvents()
    //class DeleteItem(val index: Int) : AlgorithmEvents()
    //class UpdateItem(val index: Int, val value: Int): AlgorithmEvents()
}