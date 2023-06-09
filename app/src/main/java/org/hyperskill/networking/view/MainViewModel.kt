package org.hyperskill.networking.view

import androidx.lifecycle.ViewModel
import org.hyperskill.networking.model.models.Drink

class MainViewModel : ViewModel() {

    var drinks: List<Drink> = emptyList()

    init {
        drinks = listOf(
            Drink(
                name = "Cappuccino",
                image = "",
                id = 0,
                type = "Coffee"
            ),
            Drink(
                name = "Latte",
                image = "",
                id = 1,
                type = "Coffee"
            ),
            Drink(
                name = "Espresso",
                image = "",
                id = 2,
                type = "Coffee"
            ),
            Drink(
                name = "Americano",
                image = "",
                id = 3,
                type = "Coffee"
            ),
            Drink(
                name = "Mocha",
                image = "",
                id = 4,
                type = "Coffee"
            ),
            Drink(
                name = "Macchiato",
                image = "",
                id = 5,
                type = "Coffee"
            ),
            Drink(
                name = "Flat White",
                image = "",
                id = 6,
                type = "Coffee"
            )
        )
    }

}