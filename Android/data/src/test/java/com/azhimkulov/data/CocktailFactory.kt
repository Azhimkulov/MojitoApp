package com.azhimkulov.data

import com.azhimkulov.data.entity.CocktailEntity
import com.azhimkulov.domain.model.CocktailModel
import java.util.*

class CocktailFactory {

    companion object {
        fun makeCollection(count: Int): Collection<CocktailEntity> {
            val collection = mutableListOf<CocktailEntity>()
            repeat(count) {
                collection.add(makeCocktail())
            }
            return collection
        }

        fun makeCocktail(): CocktailEntity {
            return CocktailEntity(
                Math.random().toInt().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                arrayListOf(),
                arrayListOf()
            )
        }

        fun makeModelCollection(count: Int): Collection<CocktailModel> {
            val collection = mutableListOf<CocktailModel>()
            repeat(count) {
                collection.add(makeCocktailModel())
            }
            return collection
        }

        fun makeCocktailModel(): CocktailModel {
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