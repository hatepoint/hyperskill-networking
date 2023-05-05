package org.hyperskill.networking.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.hyperskill.networking.model.models.Drink
import org.hyperskill.networking.model.retrofit.RetrofitClient

class DrinkRepository {

    private var drinks: DrinkResponse = DrinkResponse.Empty

    suspend fun getDrinks(): Flow<DrinkResponse> = flow {
        val response = RetrofitClient.retrofitClient.getDrinks()
        if (response.isSuccessful) {
            drinks = DrinkResponse.Success(response.body()!!)
            emit(drinks)
        } else {
            drinks = DrinkResponse.Error(response.message())
            emit(drinks)
        }
    }

}

sealed class DrinkResponse() {
    class Success(val drinks: List<Drink>) : DrinkResponse()
    class Error(val error: String) : DrinkResponse()
    object Empty : DrinkResponse()

}