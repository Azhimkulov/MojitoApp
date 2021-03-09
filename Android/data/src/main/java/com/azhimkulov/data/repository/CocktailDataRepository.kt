package com.azhimkulov.data.repository

import com.azhimkulov.data.entity.mapper.CocktailEntityDataMapper
import com.azhimkulov.data.persistence.realm.source.CryptoDataStoreFactory
import com.azhimkulov.data.rest.RestClient
import com.azhimkulov.data.utility.network.exception.parser.parseBadRequestWithMessage
import com.azhimkulov.data.utility.network.exception.parser.parseConnectionLostException
import com.azhimkulov.domain.model.CocktailModel
import com.azhimkulov.domain.repository.CocktailRepository
import io.reactivex.Completable
import io.reactivex.Observable

class CocktailDataRepository constructor(
    private val cryptoDataStoreFactory: CryptoDataStoreFactory,
    private val cocktailEntityDataMapper: CocktailEntityDataMapper
) : CocktailRepository {

    override fun getRandomCocktail(): Observable<CocktailModel> {
        return cryptoDataStoreFactory
            .retrieveRemoteDataStore()
            .getRandomCocktail()
            .flatMap {
                cryptoDataStoreFactory.retrieveLocaleDataStore().saveCocktail(it).andThen(Observable.just(it))
            }
            .map {
                cocktailEntityDataMapper.transform(it)
            }
            .onErrorResumeNext { error: Throwable ->
                Observable.error(parseBadRequestWithMessage(error))
            }
            .onErrorResumeNext { error: Throwable ->
                Observable.error(parseConnectionLostException(error))
            }
    }

    override fun getHistory(): Observable<Collection<CocktailModel>> {
        return cryptoDataStoreFactory
            .retrieveLocaleDataStore()
            .getHistory()
            .map {
                cocktailEntityDataMapper.transformCollection(it)
            }
    }

    override fun delete(id: String): Completable {
        return cryptoDataStoreFactory.retrieveLocaleDataStore().deleteByExternalId(id)
    }
}