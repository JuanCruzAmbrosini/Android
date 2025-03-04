package com.example.dessertclicker.ui


import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.data.DessertUiState
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel(): ViewModel() {

    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState = _uiState.asStateFlow()

    fun dessertClicked (){

        val updatedRevenue = _uiState.value.revenue + _uiState.value.currentDessert.price
        val updatedDessertSoldCount = _uiState.value.dessertsSold.plus(1)

        val desserts = Datasource.dessertList
        val dessertsSold = _uiState.value.dessertsSold

        _uiState.update { currentState ->
            currentState.copy(
                revenue = updatedRevenue,
                dessertsSold = updatedDessertSoldCount
            )
        }

        val dessertToShow = determineDessertToShow(desserts, dessertsSold)

        _uiState.update { currentState ->
            currentState.copy(
                currentDessert = dessertToShow
            )
        }

    }

    /**
     * Determine which dessert to show.
     */
    private fun determineDessertToShow(
        desserts: List<Dessert>,
        dessertsSold: Int
    ): Dessert {
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertToShow
    }

}