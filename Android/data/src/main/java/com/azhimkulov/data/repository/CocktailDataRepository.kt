package com.azhimkulov.data.repository

import com.azhimkulov.data.entity.mapper.CocktailEntityDataMapper
import com.azhimkulov.data.persistence.realm.source.CocktailDataStoreFactory
import com.azhimkulov.data.utility.network.exception.parser.parseBadRequestWithMessage
import com.azhimkulov.data.utility.network.exception.parser.parseConnectionLostException
import com.azhimkulov.domain.model.CocktailModel
import com.azhimkulov.domain.repository.CocktailRepository
import io.reactivex.Completable
import io.reactivex.Observable

class CocktailDataRepository constructor(
    private val cocktailDataStoreFactory: CocktailDataStoreFactory,
    private val cocktailEntityDataMapper: CocktailEntityDataMapper
) : CocktailRepository {

    override fun getRandomCocktail(): Observable<CocktailModel> {
        return cocktailDataStoreFactory
            .retrieveRemoteDataStore()
            .getRandomCocktail()
            .flatMap {
                cocktailDataStoreFactory.retrieveLocaleDataStore().saveCocktail(it).andThen(Observable.just(it))
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
        return cocktailDataStoreFactory
            .retrieveLocaleDataStore()
            .getHistory()
            .map {
                cocktailEntityDataMapper.transformCollection(it)
            }
    }

    override fun delete(id: String): Completable {
        return cocktailDataStoreFactory.retrieveLocaleDataStore().deleteByExternalId(id)
    }
}