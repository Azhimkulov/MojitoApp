package com.azhimkulov.data.rest.api

import com.azhimkulov.data.entity.CocktailEntity
import com.azhimkulov.data.entity.ResponseCocktailEntity
import io.reactivex.Observable
import retrofit2.http.GET

interface CocktailApi {

    @GET("random.php")
    fun getRandomCocktail(): Observable<CocktailEntity>

}