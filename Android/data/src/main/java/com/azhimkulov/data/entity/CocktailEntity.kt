package com.azhimkulov.data.entity

data class CocktailEntity(
    val idDrink:String,
    val strDrink:String,
    val strCategory:String,
    val strTags:String?,
    val strDrinkThumb:String,
    val ingredients:ArrayList<String?>,
    val measures: ArrayList<String?>
)