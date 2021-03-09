package com.azhimkulov.data.persistence.realm.executor

import com.azhimkulov.data.persistence.realm.unit_of_work.factory.RealmUnitOfWorkFactory
import javax.inject.Inject

/**
 * Created by azamat  on 2020-07-27.
 */
class RxRealmExecutorsProviderImpl @Inject constructor(private val realmUnitOfWorkFactory: RealmUnitOfWorkFactory) :
    RxRealmExecutorsProvider {

    override fun provideSingleItemQueryExecutor(): RxRealmSingleQueryExecutor {
        return RxRealmSingleQueryExecutorImpl(realmUnitOfWorkFactory)
    }

    override fun provideMultipleItemsQueryExecutor(): RxRealmMultipleItemsQueryExecutor {
        return RxRealmMultipleItemsQueryExecutorImpl(realmUnitOfWorkFactory)
    }

    override fun provideTransactionExecutor(): RxRealmTransactionExecutor {
        return RxRealmTransactionExecutorImpl(realmUnitOfWorkFactory)
    }
}