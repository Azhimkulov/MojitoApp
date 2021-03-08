package com.azhimkulov.mojitoapp.view.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.azhimkulov.domain.interactor.GetRandomCocktail
import com.azhimkulov.mojitoapp.view.viewmodel.RandomCocktailViewModel
import javax.inject.Inject

class RandomCocktailViewModelFactory @Inject constructor(
    private val getRandomCocktail: GetRandomCocktail
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RandomCocktailViewModel(getRandomCocktail) as T
    }
}