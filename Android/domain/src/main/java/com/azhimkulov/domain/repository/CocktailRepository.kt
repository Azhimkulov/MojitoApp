package com.azhimkulov.domain.repository

import com.azhimkulov.domain.model.CocktailModel
import io.reactivex.Completable
import io.reactivex.Observable

interface CocktailRepository {
    fun getRandomCocktail(): Observable<CocktailModel>
    fun getHistory(): Observable<Collection<CocktailModel>>
    fun delete(id:String): Completable
}