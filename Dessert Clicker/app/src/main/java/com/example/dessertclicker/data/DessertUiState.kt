package com.example.dessertclicker.data

import com.example.dessertclicker.model.Dessert

data class DessertUiState (

    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    val currentDessert: Dessert = Datasource.dessertList[0],

    )