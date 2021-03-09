package com.azhimkulov.data

import com.azhimkulov.data.CocktailFactory
import com.azhimkulov.data.entity.CocktailEntity
import com.azhimkulov.data.entity.mapper.CocktailEntityDataMapper
import com.azhimkulov.domain.model.CocktailModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class CocktailEntityDataMapperTest {

    private lateinit var cocktailEntityDataMapper: CocktailEntityDataMapper

    @Before
    fun setup() {
        cocktailEntityDataMapper = CocktailEntityDataMapper()
    }

    @Test
    fun mapFromEntityTest() {
        val entity = CocktailFactory.makeCocktail()
        val model = cocktailEntityDataMapper.transform(entity)

        assertCocktailDataEquality(entity, model)
    }

    private fun assertCocktailDataEquality(cocktailEntity: CocktailEntity, cocktailModel: CocktailModel) {
        assertEquals(cocktailEntity.idDrink, cocktailModel.id.toString())
        assertEquals(cocktailEntity.strCategory, cocktailModel.category)
        assertEquals(cocktailEntity.strDrink, cocktailModel.name)
        assertEquals(cocktailEntity.strDrinkThumb, cocktailModel.imageUrl)
        assertEquals(cocktailEntity.strTags, cocktailModel.type)
    }
}