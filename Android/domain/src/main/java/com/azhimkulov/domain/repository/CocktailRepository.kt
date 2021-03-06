package com.azhimkulov.domain.repository

import com.azhimkulov.domain.model.CocktailModel

interface CocktailRepository {
    fun getRandomCocktail(): CocktailModel
}