package com.azhimkulov.domain.model

import io.reactivex.observers.DisposableObserver

open class DefaultObserver<T>: DisposableObserver<T>() {
    override fun onNext(t: T) {
        //intentionally left empty
    }

    override fun onError(e: Throwable) {
        //intentionally left empty
    }

    override fun onComplete() {
        //intentionally left empty
    }
}