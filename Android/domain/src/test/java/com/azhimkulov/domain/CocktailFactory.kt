package com.azhimkulov.domain

import com.azhimkulov.domain.model.CocktailModel
import java.util.*

class CocktailFactory {
    companion object {
        fun makeCollection(count: Int): Collection<CocktailModel> {
            val collection = mutableListOf<CocktailModel>()
            repeat(count) {
                collection.add(makeCocktail())
            }
            return collection
        }

        fun makeCocktail(): CocktailModel {
            return CocktailModel(
                Math.random().toInt(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                arrayListOf()
            )
        }
    }
}