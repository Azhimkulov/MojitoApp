package com.azhimkulov.mojitoapp.internal.di.component

import android.content.Context
import com.azhimkulov.data.persistence.realm.executor.RxRealmExecutorsProvider
import com.azhimkulov.data.persistence.realm.provider.RealmProvider
import com.azhimkulov.data.persistence.realm.unit_of_work.factory.RealmUnitOfWorkFactory
import com.azhimkulov.data.rest.RestClient
import com.azhimkulov.domain.executor.PostExecutionThread
import com.azhimkulov.domain.executor.ThreadExecutor
import com.azhimkulov.mojitoapp.view.activity.MainActivity
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
    fun threadExecutor(): ThreadExecutor
    fun postExecutionThread(): PostExecutionThread
    fun restClient(): RestClient
    fun rxRealmExecutorsProvider(): RxRealmExecutorsProvider
    fun realmUnitOfWorkFactory(): RealmUnitOfWorkFactory
    fun realmProvider(): RealmProvider
}