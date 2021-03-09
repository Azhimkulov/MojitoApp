package com.azhimkulov.data.persistence.realm.entity.mapper


abstract class RealmEntityDataMapper<TRealm, TEntity> {
    abstract fun transformToRealm(entity: TEntity): TRealm
    abstract fun transformFromRealm(realmEntity: TRealm): TEntity

    fun transformEntityCollection(entities: Collection<TEntity>): Collection<TRealm> {

        val domainModels: MutableCollection<TRealm> = ArrayList(entities.size)

        for (entity in entities) {
            val domainModel = transformToRealm(entity)
            domainModels.add(domainModel)
        }

        return domainModels
    }

    fun transformRealmCollection(entities: Collection<TRealm>): Collection<TEntity> {

        val domainModels: MutableCollection<TEntity> = ArrayList(entities.size)

        for (entity in entities) {
            val domainModel = transformFromRealm(entity)
            domainModels.add(domainModel)
        }

        return domainModels
    }
}