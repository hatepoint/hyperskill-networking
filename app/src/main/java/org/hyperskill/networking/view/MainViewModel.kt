package org.hyperskill.networking.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.hyperskill.networking.model.DrinkRepository
import org.hyperskill.networking.model.models.Drink

class MainViewModel(repository: DrinkRepository) : ViewModel() {

    var drinks: List<Drink> = emptyList()

    init {
        drinks = repository.getDrinks()
    }

}