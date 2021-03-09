package com.azhimkulov.mojitoapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.azhimkulov.mojitoapp.R
import com.azhimkulov.mojitoapp.databinding.FragmentHistoryBinding
import com.azhimkulov.mojitoapp.internal.di.component.MainComponent
import com.azhimkulov.mojitoapp.view.viewmodel.HistoryViewModel
import com.azhimkulov.mojitoapp.view.viewmodel.LoadingViewModel
import com.azhimkulov.mojitoapp.view.viewmodel.factory.HistoryViewModelFactory
import javax.inject.Inject

class HistoryFragment:BaseFragment() {

    @Inject
    lateinit var historyViewModelFactory:HistoryViewModelFactory
    private val historyViewModel:HistoryViewModel by activityViewModels { historyViewModelFactory }
    private lateinit var binding: FragmentHistoryBinding

    companion object {
        fun newInstance(): HistoryFragment {
            return HistoryFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getComponent(MainComponent::class.java).inject(this)
        lifecycle.addObserver(historyViewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_history,
            container,
            false
        )
        binding.viewModel = historyViewModel
        return binding.root
    }

    override fun provideLoadingViewModel(): LoadingViewModel? {
        return historyViewModel
    }
}