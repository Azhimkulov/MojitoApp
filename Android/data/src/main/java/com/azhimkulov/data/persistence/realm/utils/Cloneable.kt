package com.azhimkulov.data.persistence.realm.utils

interface Cloneable<T> {
    fun makeShallowCopy(): T
}