package com.azhimkulov.mojitoapp.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.azhimkulov.mojitoapp.R
import com.azhimkulov.mojitoapp.internal.di.component.MainComponent
import com.azhimkulov.mojitoapp.view.viewmodel.LoadingViewModel
import com.azhimkulov.mojitoapp.view.viewmodel.SplashScreenViewModel
import com.azhimkulov.mojitoapp.view.viewmodel.factory.SplashScreenViewModelFactory
import javax.inject.Inject

class SplashFragment : BaseFragment() {

    @Inject
    lateinit var splashScreenViewModelFactory: SplashScreenViewModelFactory
    private val splashScreenViewModel: SplashScreenViewModel by activityViewModels { splashScreenViewModelFactory }

    private var interactionListener: SplashInteractionListener? = null

    interface SplashInteractionListener {
        fun viewRandomCocktailScreen()
    }

    companion object {
        fun newInstance(): SplashFragment {
            return SplashFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SplashInteractionListener) {
            interactionListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getComponent(MainComponent::class.java).inject(this)
        lifecycle.addObserver(splashScreenViewModel)

        splashScreenViewModel.onTimerCompleted.observe(context as LifecycleOwner) {
            interactionListener?.viewRandomCocktailScreen()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun provideLoadingViewModel(): LoadingViewModel {
        return splashScreenViewModel
    }
}