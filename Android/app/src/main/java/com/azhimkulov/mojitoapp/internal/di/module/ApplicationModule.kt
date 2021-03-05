package com.azhimkulov.mojitoapp.internal.di.module

import android.content.Context
import com.azhimkulov.mojitoapp.AndroidApplication
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
}