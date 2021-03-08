package com.azhimkulov.mojitoapp.view.viewmodel

import androidx.databinding.ObservableField
import com.azhimkulov.domain.interactor.GetRandomCocktail
import com.azhimkulov.domain.model.CocktailModel
import com.azhimkulov.domain.model.DefaultObserver
import com.azhimkulov.mojitoapp.R

class RandomCocktailViewModel(
    private val getRandomCocktail: GetRandomCocktail
): LoadingViewModel() {

    private var isInitialize = false

    var isLoading = ObservableField(false)
    var category = ObservableField<String>()
    var name = ObservableField<String>()

    override fun onResume() {
        super.onResume()
        if (!isInitialize) {
            isInitialize = true
            isLoading.set(true)
            getRandomCocktail.execute(CocktailObserver())
        }
    }

    private inner class CocktailObserver: DefaultObserver<CocktailModel>() {
        override fun onComplete() {
            isLoading.set(false)
        }

        override fun onNext(t: CocktailModel) {
            category.set(t.category)
            name.set(t.name)
        }

        override fun onError(e: Throwable) {
            isLoading.set(false)
            onObservableFailed(e, getString(R.string.userFriendly_errorMessage))
        }
    }
}