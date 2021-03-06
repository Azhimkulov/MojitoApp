package com.azhimkulov.data.repository

import com.azhimkulov.data.entity.mapper.CocktailEntityDataMapper
import com.azhimkulov.data.rest.RestClient
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
                cocktailEntityDataMapper.transform(it.drinks.first())
            }
    }
}