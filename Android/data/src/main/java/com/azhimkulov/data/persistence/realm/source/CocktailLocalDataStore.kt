package com.azhimkulov.data.persistence.realm.source

import com.azhimkulov.data.entity.CocktailEntity
import com.azhimkulov.data.persistence.realm.entity.mapper.CocktailRealmEntityDataMapper
import com.azhimkulov.data.persistence.realm.executor.RxRealmExecutorsProvider
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class CocktailLocalDataStore @Inject constructor(
    private val rxRealmExecutorsProvider: RxRealmExecutorsProvider,
    private val cocktailRealmEntityDataMapper: CocktailRealmEntityDataMapper
):CocktailDataStore {

    override fun getRandomCocktail(): Observable<CocktailEntity> {
        throw UnsupportedOperationException()
    }

    override fun getHistory(): Observable<Collection<CocktailEntity>> {
        return rxRealmExecutorsProvider
            .provideMultipleItemsQueryExecutor()
            .executeQuery {
                return@executeQuery it.getCocktails().get()
            }
            .map {
                cocktailRealmEntityDataMapper.transformRealmCollection(it)
            }
    }

    override fun saveCocktail(model: CocktailEntity): Completable {
        return rxRealmExecutorsProvider.provideTransactionExecutor().executeTransaction {
            val realmModel = cocktailRealmEntityDataMapper.transformToRealm(model)
            it.getCocktails().save(realmModel)
        }
    }

    override fun deleteByExternalId(id: String): Completable {
        return rxRealmExecutorsProvider.provideTransactionExecutor().executeTransaction {
            it.getCocktails().delete(id)
        }
    }
}