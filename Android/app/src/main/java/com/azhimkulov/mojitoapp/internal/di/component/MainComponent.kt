package com.azhimkulov.mojitoapp.internal.di.component

import com.azhimkulov.mojitoapp.internal.di.PerActivity
import com.azhimkulov.mojitoapp.internal.di.module.ActivityModule
import com.azhimkulov.mojitoapp.internal.di.module.MainModule
import com.azhimkulov.mojitoapp.view.fragment.RandomCocktailFragment
import com.azhimkulov.mojitoapp.view.fragment.SplashFragment
import dagger.Component

@PerActivity
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class, MainModule::class]
)
interface MainComponent {
    fun inject(splashFragment: SplashFragment)
    fun inject(randomCocktailFragment: RandomCocktailFragment)
}