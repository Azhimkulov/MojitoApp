package com.azhimkulov.mojitoapp.view.viewmodel

import androidx.databinding.ObservableField
import com.azhimkulov.domain.interactor.GetRandomCocktail
import com.azhimkulov.domain.model.CocktailModel
import com.azhimkulov.domain.model.DefaultObserver
import com.azhimkulov.domain.model.IngredientModel
import com.azhimkulov.mojitoapp.R
import com.azhimkulov.mojitoapp.view.adapter.UltimateAdapter

class RandomCocktailViewModel(
    private val getRandomCocktail: GetRandomCocktail
): LoadingViewModel(), UltimateAdapter.UltimateAdapterDataSource {

    private var isInitialize = false
    private val ingredients = mutableListOf<IngredientModel>()

    val ultimateAdapter = UltimateAdapter.newInstance()

    val isFavorite = ObservableField(false)
    val isLoading = ObservableField(false)
    val isRefreshCompleted = ObservableField(false)

    val baseImage = ObservableField<String>()
    val category = ObservableField<String>()
    val type = ObservableField<String>()
    val name = ObservableField<String>()
    val id = ObservableField<String>()

    init {
        setupAdapter()
        retryFailedRequest = { getCocktail() }
    }

    override fun onResume() {
        super.onResume()
        if (!isInitialize) {
            isInitialize = true
            isLoading.set(true)
            getCocktail()
        }
    }

    override fun recyclerView(): Int {
        return ingredients.size
    }

    override fun recyclerView(position: Int): IngredientModel {
        return ingredients[position]
    }

    fun handleOnFavoriteClicked() {
        isFavorite.set(true)
    }

    fun handleOnLayoutSwiped() {
        isRefreshCompleted.set(true)
        getCocktail()
    }

    private fun getCocktail() {
        getRandomCocktail.execute(CocktailObserver())
    }

    private fun setupAdapter() {
        ultimateAdapter.register(R.layout.row_ingredient, IngredientModel::class.java)
        ultimateAdapter.dataSource = this
    }

    private fun setCollection(models: Collection<IngredientModel>) {
        ingredients.clear()
        ingredients.addAll(models)
    }

    private fun updateCollectionView() {
        ultimateAdapter.notifyDataSetChanged()
    }

    private inner class CocktailObserver: DefaultObserver<CocktailModel>() {
        override fun onComplete() {
            isLoading.set(false)
            isRefreshCompleted.set(false)
            isFavorite.set(false)
        }

        override fun onNext(t: CocktailModel) {
            baseImage.set(t.imageUrl)
            category.set(t.category)
            type.set(t.type ?: "Unknown type")
            name.set(t.name)
            id.set(t.id.toString())

            setCollection(t.ingredients)
            updateCollectionView()
        }

        override fun onError(e: Throwable) {
            isLoading.set(false)
            isRefreshCompleted.set(false)
            onObservableFailed(e, getString(R.string.userFriendly_errorMessage))
        }
    }
}