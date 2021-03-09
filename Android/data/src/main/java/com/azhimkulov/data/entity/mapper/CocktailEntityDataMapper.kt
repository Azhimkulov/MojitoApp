package com.azhimkulov.data.entity.mapper

import com.azhimkulov.data.entity.CocktailEntity
import com.azhimkulov.domain.model.CocktailModel
import com.azhimkulov.domain.model.IngredientModel
import javax.inject.Inject

class CocktailEntityDataMapper @Inject constructor(): BaseEntityDataMapper<CocktailModel, CocktailEntity>() {
    override fun transform(entity: CocktailEntity): CocktailModel {
        return CocktailModel(
            entity.idDrink.toInt(),
            entity.strDrink,
            entity.strCategory,
            entity.strTags,
            entity.strDrinkThumb,
            getIngredient(entity)
        )
    }

    private fun getIngredient(entity: CocktailEntity):Collection<IngredientModel> {
        val collection = mutableListOf<IngredientModel>()

        for (i in entity.ingredients.indices) {
            val ingredient = entity.ingredients[i]
            val measure = entity.measures[i]
            if (!ingredient.isNullOrBlank() && !measure.isNullOrBlank() && ingredient != "null" && measure != "null") {
                collection.add(IngredientModel(ingredient, measure))
            }
        }

        return collection
    }
}