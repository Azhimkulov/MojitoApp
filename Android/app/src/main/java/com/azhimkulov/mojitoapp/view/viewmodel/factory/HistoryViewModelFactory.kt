package com.azhimkulov.mojitoapp.view.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.azhimkulov.domain.interactor.GetCocktailHistory
import com.azhimkulov.mojitoapp.view.viewmodel.HistoryViewModel
import javax.inject.Inject

class HistoryViewModelFactory @Inject constructor(
    private val getCocktailHistory: GetCocktailHistory
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HistoryViewModel(getCocktailHistory) as T
    }
}