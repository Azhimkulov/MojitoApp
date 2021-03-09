package com.azhimkulov.data.persistence.realm.repository

import com.azhimkulov.data.persistence.realm.entity.RealmCocktail
import io.realm.Realm
import io.realm.Sort

class RealmCocktailRepositoryImpl(private val realm: Realm):RealmCocktailRepository {

    companion object {
        private const val COLLECTION_LIMIT = 10
    }

    override fun get(): Collection<RealmCocktail> {
        return realm
            .where(RealmCocktail::class.java)
            .sort("createdDate", Sort.DESCENDING)
            .limit(10)
            .findAll()
    }

    override fun save(realmCocktail: RealmCocktail) {
        val count = realm
            .where(RealmCocktail::class.java)
            .count()

        if (count >= COLLECTION_LIMIT) {
            val collection = realm
                .where(RealmCocktail::class.java)
                .sort("createdDate", Sort.DESCENDING)
                .findAll()

            val realmModel = collection.last()
            realmModel?.deleteFromRealm()
        }

        realm.insertOrUpdate(realmCocktail)
    }

    override fun delete(externalId: String) {
        val model = realm.where(RealmCocktail::class.java).equalTo("externalId", externalId).findFirst()
        model?.deleteFromRealm()
    }
}