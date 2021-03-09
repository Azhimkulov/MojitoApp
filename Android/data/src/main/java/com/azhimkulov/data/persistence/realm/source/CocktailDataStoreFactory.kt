package com.azhimkulov.data.persistence.realm.source

import javax.inject.Inject

class CocktailDataStoreFactory @Inject constructor(
    private val cocktailRemoteDataStore: CocktailRemoteDataStore,
    private val cocktailLocalDataStore: CocktailLocalDataStore
) {

    fun retrieveRemoteDataStore(): CocktailDataStore {
        return cocktailRemoteDataStore
    }

    fun retrieveLocaleDataStore(): CocktailDataStore {
        return cocktailLocalDataStore
    }
}