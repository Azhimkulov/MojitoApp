package com.azhimkulov.data.persistence.realm.source

import com.azhimkulov.data.entity.CocktailEntity
import com.azhimkulov.data.rest.RestClient
import com.azhimkulov.data.utility.network.exception.parser.parseBadRequestWithMessage
import com.azhimkulov.data.utility.network.exception.parser.parseConnectionLostException
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class CocktailRemoteDataStore @Inject constructor(
    private val restClient: RestClient
):CocktailDataStore {
    override fun getRandomCocktail(): Observable<CocktailEntity> {
        return restClient
            .getCocktailApi()
            .getRandomCocktail()
    }

    override fun getHistory(): Observable<Collection<CocktailEntity>> {
        throw UnsupportedOperationException()
    }

    override fun saveCocktail(model: CocktailEntity): Completable {
        throw UnsupportedOperationException()
    }

    override fun deleteByExternalId(id: String): Completable {
        throw UnsupportedOperationException()
    }
}