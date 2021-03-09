package com.azhimkulov.data.persistence.realm.unit_of_work

import com.azhimkulov.data.persistence.realm.repository.RealmCocktailRepository

interface RealmUnitOfWork : AutoCloseable {
    fun getCocktails(): RealmCocktailRepository

    fun executeTransaction(execute: (RealmUnitOfWork) -> Unit)
}