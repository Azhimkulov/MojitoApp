package com.azhimkulov.domain

import com.azhimkulov.domain.executor.PostExecutionThread
import com.azhimkulov.domain.executor.ThreadExecutor
import com.azhimkulov.domain.interactor.GetRandomCocktail
import com.azhimkulov.domain.model.CocktailModel
import com.azhimkulov.domain.repository.CocktailRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class GetRandomCocktailTest {

    private lateinit var getRandomCocktail: GetRandomCocktail

    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var cocktailRepository: CocktailRepository

    @Before
    fun setup() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        cocktailRepository = mock()

        getRandomCocktail = GetRandomCocktail(cocktailRepository, mockThreadExecutor, mockPostExecutionThread)
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        getRandomCocktail.buildUseCaseObservable(null)
        verify(cocktailRepository).getRandomCocktail()
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        stubCocktailRepositoryGetCocktail(Observable.just(CocktailFactory.makeCocktail()))
        val testObserver = getRandomCocktail.buildUseCaseObservable(null).test()
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseObservableReturnsData() {
        val cocktail = CocktailFactory.makeCocktail()
        stubCocktailRepositoryGetCocktail(Observable.just(cocktail))
        val testObserver = getRandomCocktail.buildUseCaseObservable(null).test()
        testObserver.assertValue(cocktail)
    }

    private fun stubCocktailRepositoryGetCocktail(observable: Observable<CocktailModel>) {
        whenever(cocktailRepository.getRandomCocktail())
            .thenReturn(observable)
    }
}