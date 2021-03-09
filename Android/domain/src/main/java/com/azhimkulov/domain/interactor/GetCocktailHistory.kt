package com.azhimkulov.domain.interactor

import com.azhimkulov.domain.executor.PostExecutionThread
import com.azhimkulov.domain.executor.ThreadExecutor
import com.azhimkulov.domain.model.CocktailModel
import com.azhimkulov.domain.repository.CocktailRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetCocktailHistory @Inject constructor(
    private val cocktailRepository: CocktailRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
): UseCase<Collection<CocktailModel>, Void>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Void?): Observable<Collection<CocktailModel>> {
        val g = cocktailRepository.getHistory()
        return g
    }
}