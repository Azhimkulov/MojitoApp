package com.azhimkulov.mojitoapp.internal.di.module

import com.azhimkulov.data.entity.mapper.CocktailEntityDataMapper
import com.azhimkulov.data.repository.CocktailDataRepository
import com.azhimkulov.data.rest.RestClient
import com.azhimkulov.domain.repository.CocktailRepository
import com.azhimkulov.mojitoapp.internal.di.PerActivity
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @PerActivity
    @Provides
    fun provideCocktailRepository(
        restClient: RestClient,
        cocktailEntityDataMapper: CocktailEntityDataMapper
    ):CocktailRepository {
        return CocktailDataRepository(
            restClient, cocktailEntityDataMapper
        )
    }
}