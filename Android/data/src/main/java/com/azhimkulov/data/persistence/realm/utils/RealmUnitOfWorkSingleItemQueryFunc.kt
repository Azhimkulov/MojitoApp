package com.azhimkulov.data.persistence.realm.utils

import com.azhimkulov.data.persistence.realm.unit_of_work.RealmUnitOfWork

interface RealmUnitOfWorkSingleItemQueryFunc<T : Cloneable<T>> {

    fun performQuery(realmUnitOfWork: RealmUnitOfWork): T?
}