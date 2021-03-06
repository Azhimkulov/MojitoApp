package com.azhimkulov.data.entity.mapper

import com.azhimkulov.data.entity.CocktailEntity
import com.azhimkulov.domain.model.CocktailModel
import javax.inject.Inject

class CocktailEntityDataMapper @Inject constructor(): BaseEntityDataMapper<CocktailModel, CocktailEntity>() {
    override fun transform(entity: CocktailEntity): CocktailModel {
        return CocktailModel(
            entity.idDrink.toInt(),
            entity.strDrink,
            entity.strCategory,
            entity.strAlcoholic,
            entity.strDrinkThumb
        )
    }
}