package com.azhimkulov.domain.interactor

import com.azhimkulov.domain.executor.PostExecutionThread
import com.azhimkulov.domain.executor.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class UseCase<T, Params>(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {
    private val disposables = CompositeDisposable()

    protected abstract fun buildUseCaseObservable(params: Params?): Observable<T>

    fun execute(observer: DisposableObserver<T>, params: Params? = null) {
        val observable = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(this.threadExecutor))
            .observeOn(this.postExecutionThread.getScheduler())
        addDisposable(observable.subscribeWith(observer))
    }

    fun dispose() {
        if (!this.disposables.isDisposed) {
            this.disposables.dispose()
        }
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}