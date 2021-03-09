package com.azhimkulov.data

import com.azhimkulov.data.entity.CocktailEntity
import com.azhimkulov.data.entity.mapper.CocktailEntityDataMapper
import com.azhimkulov.data.persistence.realm.source.CocktailDataStoreFactory
import com.azhimkulov.data.persistence.realm.source.CocktailLocalDataStore
import com.azhimkulov.data.persistence.realm.source.CocktailRemoteDataStore
import com.azhimkulov.data.repository.CocktailDataRepository
import com.azhimkulov.domain.model.CocktailModel
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CocktailDataRepositoryTest {

    private lateinit var cocktailDataRepository: CocktailDataRepository

    private lateinit var cocktailDataStoreFactory: CocktailDataStoreFactory
    private lateinit var cocktailEntityDataMapper: CocktailEntityDataMapper
    private lateinit var cocktailLocalDataStore: CocktailLocalDataStore
    private lateinit var cocktailRemoteDataStore: CocktailRemoteDataStore

    @Before
    fun setup() {
        cocktailDataStoreFactory = mock()
        cocktailEntityDataMapper = mock()
        cocktailLocalDataStore = mock()
        cocktailRemoteDataStore = mock()
        cocktailDataRepository = CocktailDataRepository(cocktailDataStoreFactory, cocktailEntityDataMapper)
        stubCocktailDataStoreFactoryRetrieveLocalDataStore()
        stubCocktailDataStoreFactoryRetrieveRemoteDataStore()
    }

    @Test
    fun getRandomCocktailCompletes() {
        val entity = CocktailFactory.makeCocktail()
        val model = CocktailFactory.makeCocktailModel()
        stubCocktailRemoteDataStoreGetCocktail(Observable.just(entity))
        stubCocktailLocalDataStoreSaveCocktail(entity)
        stubCocktailMapperMapFromEntity(entity, model)
        val testObserver = cocktailDataRepository.getRandomCocktail().test()
        testObserver.assertComplete()
    }

    @Test
    fun getRandomCocktailReturnData() {
        val entity = CocktailFactory.makeCocktail()
        val model = CocktailFactory.makeCocktailModel()
        stubCocktailRemoteDataStoreGetCocktail(Observable.just(entity))
        stubCocktailLocalDataStoreSaveCocktail(entity)
        stubCocktailMapperMapFromEntity(entity, model)
        val testObserver = cocktailDataRepository.getRandomCocktail().test()
        testObserver.assertValue(model)
    }

    @Test
    fun getRandomCocktailCallLocaleDataStoreSave() {
        val entity = CocktailFactory.makeCocktail()
        val model = CocktailFactory.makeCocktailModel()
        stubCocktailRemoteDataStoreGetCocktail(Observable.just(entity))
        stubCocktailLocalDataStoreSaveCocktail(entity)
        stubCocktailMapperMapFromEntity(entity, model)
        cocktailDataRepository.getRandomCocktail().test()
        verify(cocktailLocalDataStore).saveCocktail(entity)
    }

    private fun stubCocktailDataStoreFactoryRetrieveLocalDataStore() {
        whenever(cocktailDataStoreFactory.retrieveLocaleDataStore())
            .thenReturn(cocktailLocalDataStore)
    }

    private fun stubCocktailDataStoreFactoryRetrieveRemoteDataStore() {
        whenever(cocktailDataStoreFactory.retrieveRemoteDataStore())
            .thenReturn(cocktailRemoteDataStore)
    }

    private fun stubCocktailRemoteDataStoreGetCocktail(observable: Observable<CocktailEntity>) {
        whenever(cocktailRemoteDataStore.getRandomCocktail())
            .thenReturn(observable)
    }

    private fun stubCocktailLocalDataStoreSaveCocktail(entity: CocktailEntity) {
        whenever(cocktailLocalDataStore.saveCocktail(entity))
            .thenReturn(Completable.complete())
    }

    private fun stubCocktailMapperMapFromEntity(
        cocktailEntity: CocktailEntity,
        cocktailModel: CocktailModel
    ) {
        whenever(cocktailEntityDataMapper.transform(cocktailEntity))
            .thenReturn(cocktailModel)
    }
}