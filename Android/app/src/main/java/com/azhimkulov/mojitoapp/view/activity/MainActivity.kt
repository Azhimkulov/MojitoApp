package com.azhimkulov.mojitoapp.view.activity

import android.os.Bundle
import com.azhimkulov.mojitoapp.R
import com.azhimkulov.mojitoapp.internal.di.HasComponent
import com.azhimkulov.mojitoapp.internal.di.component.DaggerMainComponent
import com.azhimkulov.mojitoapp.internal.di.component.MainComponent
import com.azhimkulov.mojitoapp.internal.di.module.MainModule
import com.azhimkulov.mojitoapp.view.fragment.HistoryFragment
import com.azhimkulov.mojitoapp.view.fragment.RandomCocktailFragment
import com.azhimkulov.mojitoapp.view.fragment.SplashFragment
import kotlinx.android.synthetic.main.activity_with_fragment.*

class MainActivity : BaseActivity(),
    SplashFragment.SplashInteractionListener,
    RandomCocktailFragment.InteractionListener,
    HasComponent<MainComponent> {
    private var mainComponent: MainComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_fragment)
        hideActionBar()
        hideStatsBar()

        initializeInjector()
        injectActivity()

        initializeFirstFragment()
    }

    override fun getComponent(): MainComponent {
        if (mainComponent == null) {
            initializeInjector()
        }
        return mainComponent!!
    }

    override fun viewRandomCocktailScreen() {
        replaceFragment(container_for_fragment, RandomCocktailFragment.newInstance())
    }

    private fun initializeFirstFragment() {
        replaceFragment(container_for_fragment, SplashFragment.newInstance())
    }

    override fun viewHistoryScreen() {
        addFragment(container_for_fragment, HistoryFragment.newInstance())
    }

    private fun injectActivity() {
        getApplicationComponent().inject(this)
    }

    private fun initializeInjector() {
        this.mainComponent = DaggerMainComponent.builder()
            .applicationComponent(getApplicationComponent())
            .activityModule(getActivityModule())
            .mainModule(MainModule())
            .build()
    }
}