package com.azhimkulov.data.persistence.realm.unit_of_work.factory

import com.azhimkulov.data.persistence.realm.provider.RealmProvider
import com.azhimkulov.data.persistence.realm.unit_of_work.RealmUnitOfWork
import com.azhimkulov.data.persistence.realm.unit_of_work.RealmUnitOfWorkImpl

class RealmUnitOfWorkFactoryImpl(private val realmProvider: RealmProvider) :
    RealmUnitOfWorkFactory {

    override fun create(): RealmUnitOfWork {
        return RealmUnitOfWorkImpl(this.realmProvider.provide())
    }
}