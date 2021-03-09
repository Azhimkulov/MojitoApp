package com.azhimkulov.domain.model

data class CocktailModel(
    val id: Int,
    val name: String,
    val category: String,
    val type: String?,
    val imageUrl: String,
    val ingredients: Collection<IngredientModel>
)