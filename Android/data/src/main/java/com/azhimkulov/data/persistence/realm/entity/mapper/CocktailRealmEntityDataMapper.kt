package com.azhimkulov.data.persistence.realm.entity.mapper

import com.azhimkulov.data.entity.CocktailEntity
import com.azhimkulov.data.persistence.realm.entity.RealmCocktail
import com.azhimkulov.data.persistence.realm.entity.mapper.RealmEntityDataMapper
import javax.inject.Inject

class CocktailRealmEntityDataMapper @Inject constructor(): RealmEntityDataMapper<RealmCocktail, CocktailEntity>() {
    override fun transformToRealm(entity: CocktailEntity): RealmCocktail {
        val cocktail = RealmCocktail()
        cocktail.externalId = entity.idDrink
        cocktail.name = entity.strDrink
        cocktail.category = entity.strCategory
        cocktail.baseImage = entity.strDrinkThumb
        cocktail.type = entity.strTags
        return cocktail
    }

    override fun transformFromRealm(realmEntity: RealmCocktail): CocktailEntity {
        return CocktailEntity(
            realmEntity.externalId!!,
            realmEntity.name!!,
            realmEntity.category!!,
            realmEntity.type,
            realmEntity.baseImage!!,
            arrayListOf(),
            arrayListOf()
        )
    }
}