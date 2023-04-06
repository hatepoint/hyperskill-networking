package org.hyperskill.networking.model.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.JsonTransformingSerializer

@Serializable
class Drink(
    val id: Int,
    val image: String,
    val name: String,
    val type: String
)

object DrinkItemListSerializer : JsonTransformingSerializer<List<Drink>>(ListSerializer(Drink.serializer()))
