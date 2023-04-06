package org.hyperskill.networking.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.hyperskill.networking.model.DrinkRepository

/**
 * A class that implements the ViewModelProvider.Factory interface to provide an instance of MainViewModel
 * @param repository The DrinkRepository instance to pass to the MainViewModel constructor
 */
class MainViewModelFactory(private val repository: DrinkRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}