package com.azhimkulov.data.persistence.realm.unit_of_work.factory

import com.azhimkulov.data.persistence.realm.unit_of_work.RealmUnitOfWork

interface RealmUnitOfWorkFactory {
    fun create(): RealmUnitOfWork
}