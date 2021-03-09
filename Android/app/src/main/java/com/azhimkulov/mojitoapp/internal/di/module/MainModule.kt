package com.azhimkulov.mojitoapp.internal.di.module

import com.azhimkulov.data.entity.mapper.CocktailEntityDataMapper
import com.azhimkulov.data.persistence.realm.source.CocktailDataStoreFactory
import com.azhimkulov.data.repository.CocktailDataRepository
import com.azhimkulov.domain.repository.CocktailRepository
import com.azhimkulov.mojitoapp.internal.di.PerActivity
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @PerActivity
    @Provides
    fun provideCocktailRepository(
        cocktailDataStoreFactory: CocktailDataStoreFactory,
        cocktailEntityDataMapper: CocktailEntityDataMapper
    ):CocktailRepository {
        return CocktailDataRepository(
            cocktailDataStoreFactory, cocktailEntityDataMapper
        )
    }
}