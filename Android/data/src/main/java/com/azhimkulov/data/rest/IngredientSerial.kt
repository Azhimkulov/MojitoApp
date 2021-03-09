package com.azhimkulov.data.rest

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class IngredientSerial : JsonDeserializer<Collection<String>> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Collection<String> {
        Log.d("OBSERVER", json.toString())
        return mutableListOf()
    }
}