package com.azhimkulov.data.persistence.realm.executor

import com.azhimkulov.data.exception.ObjectNotFound
import com.azhimkulov.data.persistence.realm.unit_of_work.RealmUnitOfWork
import com.azhimkulov.data.persistence.realm.unit_of_work.factory.RealmUnitOfWorkFactory
import io.reactivex.Observable
import javax.inject.Inject
import com.azhimkulov.data.persistence.realm.utils.Cloneable


/**
 * Created by azamat  on 2020-07-27.
 */
class RxRealmSingleQueryExecutorImpl @Inject constructor(
    private val realmUnitOfWorkFactory: RealmUnitOfWorkFactory
) : RxRealmSingleQueryExecutor {


    override fun <T : Cloneable<T>> executeQuery(performQuery: (RealmUnitOfWork) -> T?): Observable<T> {
        return Observable.create { emitter ->
            this.realmUnitOfWorkFactory.create().use { realmUnitOfWork ->
                val result = performQuery(realmUnitOfWork)

                if (result == null) {
                    emitter.onError(ObjectNotFound())
                    return@use
                }

                emitter.onNext(result.makeShallowCopy())
                emitter.onComplete()
            }
        }
    }
}