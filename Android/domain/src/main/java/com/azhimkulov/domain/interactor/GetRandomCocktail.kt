package com.azhimkulov.domain.interactor

import com.azhimkulov.domain.executor.PostExecutionThread
import com.azhimkulov.domain.executor.ThreadExecutor
import com.azhimkulov.domain.model.CocktailModel
import com.azhimkulov.domain.repository.CocktailRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetRandomCocktail @Inject constructor(
    private val cocktailRepository: CocktailRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
): UseCase<CocktailModel, Void>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Observable<CocktailModel> {
        return cocktailRepository.getRandomCocktail()
    }
}