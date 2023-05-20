package org.hyperskill.networking.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.hyperskill.networking.model.models.Drink
import org.hyperskill.networking.model.retrofit.RetrofitClient

class MainViewModel: ViewModel() {

    // Declare private and public variables
    private val retrofitClient = RetrofitClient.retrofitClient
    private val _uiState = MutableStateFlow<MainState>(MainState.Loading)
    val uiState: StateFlow<MainState> = _uiState


    private suspend fun getDrinks(): Flow<MainState> = flow {
        val response = retrofitClient.getDrinks()
        if (response.isSuccessful) {
            emit(MainState.Success(response.body()!!))
        } else {
            emit(MainState.Error(response.message()))
        }
    }
    init {
        viewModelScope.launch {
            _uiState.value = MainState.Loading
            getDrinks().collect {
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