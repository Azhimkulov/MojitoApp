package com.azhimkulov.data.persistence.realm.repository

import com.azhimkulov.data.persistence.realm.entity.RealmCocktail
import io.realm.Realm

class RealmCocktailRepositoryImpl(private val realm: Realm):RealmCocktailRepository {

    companion object {
        private const val COLLECTION_LIMIT = 10
    }

    override fun get(): Collection<RealmCocktail> {
        return realm
            .where(RealmCocktail::class.java)
            .sort("createdDate")
            .findAll()
            .subList(0, COLLECTION_LIMIT)
    }

    override fun save(realmCocktail: RealmCocktail) {
        realm.insertOrUpdate(realmCocktail)
    }

    override fun delete(externalId: String) {
        val model = realm.where(RealmCocktail::class.java).equalTo("externalId", externalId).findFirst()
        model?.deleteFromRealm()
    }
}