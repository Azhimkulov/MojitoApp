package com.azhimkulov.data.repository

import com.azhimkulov.data.entity.mapper.CocktailEntityDataMapper
import com.azhimkulov.data.rest.RestClient
import com.azhimkulov.data.utility.network.exception.parser.parseBadRequestWithMessage
import com.azhimkulov.data.utility.network.exception.parser.parseConnectionLostException
import com.azhimkulov.domain.model.CocktailModel
import com.azhimkulov.domain.repository.CocktailRepository
import io.reactivex.Observable

class CocktailDataRepository constructor(
    private val restClient: RestClient,
    private val cocktailEntityDataMapper: CocktailEntityDataMapper
) : CocktailRepository {
    override fun getRandomCocktail(): Observable<CocktailModel> {
        return restClient
            .getCocktailApi()
            .getRandomCocktail()
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
}