package com.azhimkulov.domain.interactor

import com.azhimkulov.domain.executor.PostExecutionThread
import com.azhimkulov.domain.executor.ThreadExecutor
import com.azhimkulov.domain.interactor.timer.TimerUseCaseFactory
import com.azhimkulov.domain.model.CocktailModel
import io.reactivex.Observable

class LoadSplashScreen(
    timerUseCaseFactory: TimerUseCaseFactory,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread) :
    UseCase<CocktailModel, Void>(
        threadExecutor,
        postExecutionThread
    ) {

    override fun buildUseCaseObservable(params: Void?): Observable<CocktailModel> {
        TODO("Not yet implemented")
    }
}