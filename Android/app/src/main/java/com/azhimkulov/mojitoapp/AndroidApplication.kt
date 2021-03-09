package com.azhimkulov.mojitoapp

import android.app.Application
import com.azhimkulov.mojitoapp.internal.di.component.ApplicationComponent
import com.azhimkulov.mojitoapp.internal.di.component.DaggerApplicationComponent
import com.azhimkulov.mojitoapp.internal.di.module.ApplicationModule
import io.realm.Realm

/**
 * Created by azamat  on 3/5/21.
 */
class AndroidApplication: Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initializePersistenceStorage()
        initializeInjector()
    }

    private fun initializePersistenceStorage() {
        Realm.init(this)
    }

    private fun initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}