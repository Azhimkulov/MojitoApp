package com.azhimkulov.data.persistence.realm.executor

import com.azhimkulov.data.persistence.realm.unit_of_work.RealmUnitOfWork
import io.reactivex.Completable


/**
 * Created by azamat  on 2020-07-27.
 */
interface RxRealmTransactionExecutor {
    fun executeTransaction(execute: (RealmUnitOfWork) -> Unit): Completable
}