package org.hyperskill.networking.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.hyperskill.networking.model.DrinkResponse
import org.hyperskill.networking.model.DrinkRepository
import org.hyperskill.networking.model.models.Drink

class MainViewModel(repository: DrinkRepository) : ViewModel() {

    // Declare private and public variables
    private val _uiState = MutableStateFlow<MainState>(MainState.Loading)
    val uiState: StateFlow<MainState> = _uiState

    private fun mapToMainState(drinkResponse: DrinkResponse): MainState {
        return when (drinkResponse) {
            is DrinkResponse.Success -> MainState.Success(drinkResponse.drinks)
            is DrinkResponse.Error -> MainState.Error(drinkResponse.error)
            DrinkResponse.Empty -> MainState.Loading
        }
    }


    init {
        viewModelScope.launch {
            _uiState.value = MainState.Loading
            repository.getDrinks().map {
                mapToMainState(it)
            }.collect {
                _uiState.value = it
            }
        }
    }
}

sealed class MainState {
    object Loading : MainState()
    class Success(val drinks: List<Drink>) : MainState()
    class Error(val error: String) : MainState()
}