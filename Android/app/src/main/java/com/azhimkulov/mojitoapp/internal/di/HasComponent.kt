package com.azhimkulov.mojitoapp.internal.di

/**
 * Created by azamat  on 3/5/21.
 */
interface HasComponent<C> {
    fun getComponent(): C
}