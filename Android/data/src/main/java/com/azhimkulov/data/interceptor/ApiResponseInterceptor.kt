package com.azhimkulov.data.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject

class ApiResponseInterceptor:Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val body = response.body
        val apiResponse = body?.string()
        body?.close()

        val contentType = response.body?.contentType()

        val newResponse = if (apiResponse != null && JSONObject(apiResponse).has("drinks")) {
            val responseJson = JSONObject(apiResponse).getJSONArray("drinks").getJSONObject(0)
            val ingredient = convertAllIngredientToArray(responseJson)
            val measure = convertAllMeasureToArray(responseJson)
            responseJson.put("ingredients", ingredient)
            responseJson.put("measures", measure)

            Log.d("OBSERVER", responseJson.toString())

            response.newBuilder().body(ResponseBody.create(contentType, responseJson.toString()))
        } else {
            response.newBuilder().body(ResponseBody.create(contentType, apiResponse ?: ""))
        }
        return newResponse.build()
    }

    private fun convertAllIngredientToArray(jsonObject:JSONObject):JSONArray {
        val originalKey = "strIngredient"
        var position = 1

        val collection = mutableListOf<String>()

        while (jsonObject.has("$originalKey$position")) {
            collection.add(jsonObject.getString("$originalKey$position"))
            position++
        }

        val jsonArray = JSONArray()

        for (item in collection) {
            jsonArray.put(item)
        }

        return jsonArray
    }

    private fun convertAllMeasureToArray(jsonObject:JSONObject):JSONArray {
        val originalKey = "strMeasure"
        var position = 1

        val collection = mutableListOf<String>()

        while (jsonObject.has("$originalKey$position")) {
            collection.add(jsonObject.getString("$originalKey$position"))
            position++
        }

        val jsonArray = JSONArray()

        for (item in collection) {
            jsonArray.put(item)
        }

        return jsonArray
    }
}