package com.azhimkulov.mojitoapp.view.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.azhimkulov.data.exception.ConnectionLostException
import com.azhimkulov.mojitoapp.R
import com.azhimkulov.mojitoapp.exception.ErrorMessageFactory
import com.azhimkulov.mojitoapp.model.ToastDuration

abstract class LoadingViewModel : LifecycleObserverViewModel() {

    val setToast = MutableLiveData<Pair<String, ToastDuration>>()
    var retryFailedRequest: (() -> Unit)? = null
    lateinit var onCollectionLost: () -> Unit
    lateinit var context: () -> Context?

    fun onObservableFailed(throwable: Throwable, defaultErrorMessage: String) {
        Log.d(
            getString(R.string.observableFailed_tag),
            throwable.message ?: getString(R.string.defaultThrowable_emptyMessage)
        )

        if (throwable is ConnectionLostException) {
            showConnectionExceptionAlertDialog()
            return
        }

        val errorMessage = ErrorMessageFactory.create(throwable)
        showToast(errorMessage ?: defaultErrorMessage, ToastDuration.SHORT)
    }

    fun showToast(message: String, duration: ToastDuration) {
        setToast.value = Pair(message, duration)
    }

    protected fun getString(resourceId: Int): String {
        return context()!!.getString(resourceId)
    }

    private fun showConnectionExceptionAlertDialog() {
        onCollectionLost()
    }
}