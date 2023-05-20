package org.hyperskill.networking.model.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

object RetrofitClient {
    private val BASE_URL = "https://coffee-38472-default-rtdb.firebaseio.com"

    @OptIn(ExperimentalSerializationApi::class)
    val retrofitClient = retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(DrinksApi::class.java)
}