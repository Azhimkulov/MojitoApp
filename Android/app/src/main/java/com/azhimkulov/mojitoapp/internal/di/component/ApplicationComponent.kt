package com.azhimkulov.mojitoapp.internal.di.component

import android.content.Context
import com.azhimkulov.mojitoapp.MainActivity
import com.azhimkulov.mojitoapp.internal.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by azamat  on 3/5/21.
 */
@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)

    fun context(): Context
}