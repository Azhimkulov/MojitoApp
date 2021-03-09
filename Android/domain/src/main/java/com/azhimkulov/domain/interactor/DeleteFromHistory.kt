package com.azhimkulov.domain.interactor

import com.azhimkulov.domain.executor.PostExecutionThread
import com.azhimkulov.domain.executor.ThreadExecutor
import com.azhimkulov.domain.repository.CocktailRepository
import io.reactivex.Completable
import javax.inject.Inject

class DeleteFromHistory @Inject constructor(
    private val cocktailRepository: CocktailRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread) :
    CompletableUseCase<String>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: String?): Completable {
        return cocktailRepository.delete(params!!)
    }
}