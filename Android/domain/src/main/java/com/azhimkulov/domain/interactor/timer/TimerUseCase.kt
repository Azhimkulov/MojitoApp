package com.azhimkulov.domain.interactor.timer

import com.azhimkulov.domain.executor.PostExecutionThread
import com.azhimkulov.domain.executor.ThreadExecutor
import com.azhimkulov.domain.interactor.UseCase
import io.reactivex.Observable

class TimerUseCase(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
): UseCase<Long, TimerOptions>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseObservable(params: TimerOptions?): Observable<Long> {
        return Observable.interval(
            params!!.delay,
            params.updatePeriod,
            params.timeUnit
        )
    }
}