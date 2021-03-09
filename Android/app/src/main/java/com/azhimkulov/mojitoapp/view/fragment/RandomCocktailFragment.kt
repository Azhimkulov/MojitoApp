package com.azhimkulov.mojitoapp.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.azhimkulov.mojitoapp.R
import com.azhimkulov.mojitoapp.databinding.FragmentRandomCocktailBinding
import com.azhimkulov.mojitoapp.internal.di.component.MainComponent
import com.azhimkulov.mojitoapp.view.viewmodel.LoadingViewModel
import com.azhimkulov.mojitoapp.view.viewmodel.RandomCocktailViewModel
import com.azhimkulov.mojitoapp.view.viewmodel.factory.RandomCocktailViewModelFactory
import kotlinx.android.synthetic.main.fragment_random_cocktail.*
import javax.inject.Inject

class RandomCocktailFragment : BaseFragment() {

    @Inject
    lateinit var randomCocktailViewModelFactory: RandomCocktailViewModelFactory
    private val randomCocktailViewModel: RandomCocktailViewModel by activityViewModels { randomCocktailViewModelFactory }
    private lateinit var binding: FragmentRandomCocktailBinding
    private var interactionListener: InteractionListener? = null

    interface InteractionListener {
        fun viewHistoryScreen()
    }

    companion object {
        fun newInstance(): RandomCocktailFragment {
            return RandomCocktailFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is InteractionListener) {
            interactionListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getComponent(MainComponent::class.java).inject(this)
        lifecycle.addObserver(randomCocktailViewModel)
        randomCocktailViewModel.viewHistoryScreen.observe(context as LifecycleOwner) {
            interactionListener?.viewHistoryScreen()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_random_cocktail,
                container,
                false
            )
        binding.viewModel = randomCocktailViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeLayout.setOnRefreshListener {
            randomCocktailViewModel.handleOnLayoutSwiped()
        }
    }

    override fun provideLoadingViewModel(): LoadingViewModel {
        return randomCocktailViewModel
    }
}