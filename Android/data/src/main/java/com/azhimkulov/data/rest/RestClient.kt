package com.azhimkulov.data.rest

import com.azhimkulov.data.rest.api.CocktailApi

interface RestClient {
    fun getCocktailApi(): CocktailApi
}