package com.azhimkulov.data.persistence.realm.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*
import com.azhimkulov.data.persistence.realm.utils.Cloneable

open class RealmCocktail: RealmObject(), Cloneable<RealmCocktail> {
    @PrimaryKey
    open var internalId: String = UUID.randomUUID().toString()
    open var createdDate: Date = Date()
    open var externalId: String? = null
    open var baseImage: String? = null
    open var name: String? = null
    open var category: String? = null
    open var type: String? = null


    override fun makeShallowCopy(): RealmCocktail {
        val cocktail = RealmCocktail()
        cocktail.internalId = internalId
        cocktail.externalId = externalId
        cocktail.baseImage = baseImage
        cocktail.name = name
        cocktail.category = category
        cocktail.type = type
        cocktail.createdDate = createdDate
        return cocktail
    }
}