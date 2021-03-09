package com.azhimkulov.data.persistence.realm.unit_of_work

import com.azhimkulov.data.persistence.realm.repository.RealmCocktailRepository
import com.azhimkulov.data.persistence.realm.repository.RealmCocktailRepositoryImpl
import io.realm.Realm

class RealmUnitOfWorkImpl(
    private val realm: Realm
) : RealmUnitOfWork {

    private val realmCocktailRepository = RealmCocktailRepositoryImpl(realm)

    override fun getCocktails(): RealmCocktailRepository {
        return realmCocktailRepository
    }

    override fun executeTransaction(execute: (RealmUnitOfWork) -> Unit) {
        realm.executeTransaction {
            execute(this)
        }
    }

    override fun close() {
        realm.close()
    }
}