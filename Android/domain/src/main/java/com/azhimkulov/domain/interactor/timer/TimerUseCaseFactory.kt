package com.azhimkulov.domain.interactor.timer

import com.azhimkulov.domain.executor.PostExecutionThread
import com.azhimkulov.domain.executor.ThreadExecutor
import javax.inject.Inject

class TimerUseCaseFactory @Inject constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    fun create(): TimerUseCase {
        return TimerUseCase(threadExecutor, postExecutionThread)
    }
}