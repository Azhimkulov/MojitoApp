package com.azhimkulov.mojitoapp.view.viewmodel

import androidx.lifecycle.MutableLiveData
import com.azhimkulov.domain.interactor.GetRandomCocktail
import com.azhimkulov.domain.interactor.timer.TimerOptions
import com.azhimkulov.domain.interactor.timer.TimerUseCaseFactory
import com.azhimkulov.domain.model.CocktailModel
import com.azhimkulov.domain.model.DefaultObserver
import com.azhimkulov.mojitoapp.R
import com.azhimkulov.mojitoapp.model.ToastDuration
import io.reactivex.observers.DisposableObserver
import java.util.concurrent.TimeUnit

class SplashScreenViewModel(
    timerUseCaseFactory: TimerUseCaseFactory
) : LoadingViewModel() {

    private var isInitialize = false
    val onTimerCompleted = MutableLiveData<Boolean>()

    private val timerUseCase = timerUseCaseFactory.create()
    private val timerOptions = TimerOptions(TIMER_DELAY_IN_SECONDS, TIMER_UPDATE_IN_SECONDS, TimeUnit.SECONDS)

    companion object {
        private const val TIMER_DELAY_IN_SECONDS = 0L
        private const val TIMER_UPDATE_IN_SECONDS = 1L
        private const val TIME_OUT_IN_SECONDS = 5L
    }

    override fun onResume() {
        super.onResume()
        if (!isInitialize) {
            isInitialize = true
            timerUseCase.execute(TimerObserver(), timerOptions)
        }
    }

    private inner class TimerObserver : DefaultObserver<Long>() {
        override fun onComplete() {
        }

        override fun onNext(t: Long) {
            if (t >= TIME_OUT_IN_SECONDS) {
                onTimerCompleted.value = true
                timerUseCase.dispose()
            }
        }
    }
}