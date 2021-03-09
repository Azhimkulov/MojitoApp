package com.azhimkulov.data.persistence.realm.executor


interface RxRealmExecutorsProvider {
    fun provideSingleItemQueryExecutor(): RxRealmSingleQueryExecutor
    fun provideMultipleItemsQueryExecutor(): RxRealmMultipleItemsQueryExecutor
    fun provideTransactionExecutor(): RxRealmTransactionExecutor
}