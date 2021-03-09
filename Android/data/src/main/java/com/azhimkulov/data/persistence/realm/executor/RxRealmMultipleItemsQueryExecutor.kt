package com.azhimkulov.data.persistence.realm.executor

import com.azhimkulov.data.persistence.realm.unit_of_work.RealmUnitOfWork
import io.reactivex.Observable
import com.azhimkulov.data.persistence.realm.utils.Cloneable


/**
 * Created by azamat  on 2/22/21.
 */
interface RxRealmMultipleItemsQueryExecutor {
    fun <T : Cloneable<T>> executeQuery(
        performQuery:(RealmUnitOfWork) -> Collection<T?>?
    ): Observable<Collection<T>>
}