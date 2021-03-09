package com.azhimkulov.data.persistence.realm.repository

import com.azhimkulov.data.persistence.realm.entity.RealmCocktail

interface RealmCocktailRepository {
    fun get(): Collection<RealmCocktail>
    fun save(realmCocktail: RealmCocktail)
    fun delete(externalId:String)
}