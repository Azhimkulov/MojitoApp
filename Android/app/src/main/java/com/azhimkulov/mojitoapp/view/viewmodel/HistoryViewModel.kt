package com.azhimkulov.mojitoapp.view.viewmodel

import android.view.View
import androidx.databinding.ObservableField
import com.azhimkulov.domain.interactor.DeleteFromHistory
import com.azhimkulov.domain.interactor.GetCocktailHistory
import com.azhimkulov.domain.model.CocktailModel
import com.azhimkulov.domain.model.DefaultObserver
import com.azhimkulov.mojitoapp.R
import com.azhimkulov.mojitoapp.model.ToastDuration
import com.azhimkulov.mojitoapp.view.adapter.UltimateAdapter
import io.reactivex.observers.DisposableCompletableObserver

class HistoryViewModel(
    private val deleteFromHistory: DeleteFromHistory,
    private val getCocktailHistory: GetCocktailHistory
) : LoadingViewModel(), UltimateAdapter.UltimateAdapterDataSource,
    UltimateAdapter.UltimateAdapterInterceptor {

    private val collection = mutableListOf<CocktailModel>()

    val ultimateAdapter = UltimateAdapter.newInstance()
    val isLoading = ObservableField(false)

    init {
        setupAdapter()
    }

    override fun onResume() {
        super.onResume()
        getHistory()
    }

    override fun recyclerView(): Int {
        return collection.size
    }

    override fun recyclerView(position: Int): CocktailModel {
        return collection[position]
    }

    override fun recyclerView(view: View, position: Int) {
        val model = collection[position]
        when(view.id) {
            R.id.imageView_delete ->
                deleteFromHistory.execute(DeleteObserver(), model.id.toString())
            R.id.containerView -> {
                showToast(model.id.toString(), ToastDuration.LONG)
            }
        }
    }

    private fun setupAdapter() {
        ultimateAdapter.register(R.layout.row_cocktail, CocktailModel::class.java)
        ultimateAdapter.dataSource = this
        ultimateAdapter.interceptor = this
    }

    private fun getHistory() {
        isLoading.set(true)
        getCocktailHistory.execute(HistoryObserver())
    }

    private inner class DeleteObserver: DisposableCompletableObserver() {
        override fun onComplete() {
            getHistory()
        }

        override fun onError(e: Throwable) {
            onObservableFailed(e, getString(R.string.userFriendly_errorMessage))
        }
    }

    private inner class HistoryObserver : DefaultObserver<Collection<CocktailModel>>() {
        override fun onComplete() {
            isLoading.set(false)
        }

        override fun onNext(t: Collection<CocktailModel>) {
            collection.clear()
            collection.addAll(t)
            ultimateAdapter.notifyDataSetChanged()
        }

        override fun onError(e: Throwable) {
            isLoading.set(false)
            onObservableFailed(e, getString(R.string.userFriendly_errorMessage))
        }
    }
}