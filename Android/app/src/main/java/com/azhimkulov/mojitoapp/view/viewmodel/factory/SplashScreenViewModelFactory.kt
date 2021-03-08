package com.azhimkulov.mojitoapp.view.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.azhimkulov.domain.interactor.GetRandomCocktail
import com.azhimkulov.domain.interactor.timer.TimerUseCaseFactory
import com.azhimkulov.mojitoapp.view.viewmodel.SplashScreenViewModel
import javax.inject.Inject

class SplashScreenViewModelFactory @Inject constructor(
    private val timerUseCaseFactory: TimerUseCaseFactory
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SplashScreenViewModel(timerUseCaseFactory) as T
    }
}