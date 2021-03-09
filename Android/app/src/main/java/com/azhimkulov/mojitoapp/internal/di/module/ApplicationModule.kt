package com.azhimkulov.mojitoapp.internal.di.module

import android.content.Context
import com.azhimkulov.data.executor.JobExecutor
import com.azhimkulov.data.persistence.realm.executor.RxRealmExecutorsProvider
import com.azhimkulov.data.persistence.realm.executor.RxRealmExecutorsProviderImpl
import com.azhimkulov.data.persistence.realm.provider.RealmProvider
import com.azhimkulov.data.persistence.realm.provider.RealmProviderImpl
import com.azhimkulov.data.persistence.realm.unit_of_work.factory.RealmUnitOfWorkFactory
import com.azhimkulov.data.persistence.realm.unit_of_work.factory.RealmUnitOfWorkFactoryImpl
import com.azhimkulov.data.rest.RestClient
import com.azhimkulov.data.rest.RestClientImpl
import com.azhimkulov.domain.executor.PostExecutionThread
import com.azhimkulov.domain.executor.ThreadExecutor
import com.azhimkulov.mojitoapp.AndroidApplication
import com.azhimkulov.mojitoapp.UIThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by azamat  on 3/5/21.
 */

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @Singleton
    fun provideRestClient(): RestClient {
        return RestClientImpl("https://www.thecocktaildb.com/api/json/v1/1/")
    }

    @Provides
    @Singleton
    fun provideRxRealmExecutorsProvider(realmUnitOfWorkFactory: RealmUnitOfWorkFactory): RxRealmExecutorsProvider {
        return RxRealmExecutorsProviderImpl(realmUnitOfWorkFactory)
    }

    @Provides
    @Singleton
    fun provideRealmUnitOfWorkFactory(realmProvider: RealmProvider): RealmUnitOfWorkFactory {
        return RealmUnitOfWorkFactoryImpl(realmProvider)
    }

    @Provides
    @Singleton
    fun provideRealmProvider(): RealmProvider {
        return RealmProviderImpl()
    }
}