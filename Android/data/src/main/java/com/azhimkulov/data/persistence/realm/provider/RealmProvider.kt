package com.azhimkulov.data.persistence.realm.provider

import io.realm.Realm

interface RealmProvider {
    fun provide(): Realm
}