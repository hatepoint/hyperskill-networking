package org.hyperskill.networking.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.hyperskill.networking.model.DrinkRepository
import org.hyperskill.networking.model.models.Drink

class MainViewModel(repository: DrinkRepository) : ViewModel() {

    // Declare private and public variables
    private val _drinks = MutableStateFlow<List<Drink>>(emptyList())
    val drinks: StateFlow<List<Drink>> = _drinks

    init {
        viewModelScope.launch {
            // Collect the drinks flow from the repository and update the mutable state flow
            repository.getDrinks().collect {
                _drinks.value = it
            }
        }
    }

}