package com.azhimkulov.mojitoapp

import android.app.Application
import com.azhimkulov.mojitoapp.internal.di.component.ApplicationComponent
import com.azhimkulov.mojitoapp.internal.di.component.DaggerApplicationComponent
import com.azhimkulov.mojitoapp.internal.di.module.ApplicationModule

/**
 * Created by azamat  on 3/5/21.
 */
class AndroidApplication: Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initializeInjector()
    }

    private fun initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}