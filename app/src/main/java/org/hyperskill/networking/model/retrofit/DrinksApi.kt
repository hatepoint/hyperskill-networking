package org.hyperskill.networking.model.retrofit

import org.hyperskill.networking.model.models.Drink
import retrofit2.Response
import retrofit2.http.GET

interface DrinksApi {
    @GET("drinks.json")
    suspend fun getDrinks(): Response<List<Drink>>
}