package com.azhimkulov.data.persistence.realm.source

import com.azhimkulov.data.entity.CocktailEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface CocktailDataStore {
    fun getRandomCocktail(): Observable<CocktailEntity>

    fun getHistory(): Observable<Collection<CocktailEntity>>
    fun saveCocktail(model: CocktailEntity): Completable
    fun deleteByExternalId(id: String): Completable
}