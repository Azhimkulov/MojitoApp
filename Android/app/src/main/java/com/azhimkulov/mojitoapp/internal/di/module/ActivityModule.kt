package com.azhimkulov.mojitoapp.internal.di.module

import android.app.Activity
import com.azhimkulov.mojitoapp.internal.di.PerActivity
import dagger.Module
import dagger.Provides

/**
 * Created by azamat  on 3/5/21.
 */
@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @PerActivity
    fun provideActivity(): Activity {
        return activity
    }
}