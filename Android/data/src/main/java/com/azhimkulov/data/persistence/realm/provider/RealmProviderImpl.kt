package com.azhimkulov.data.persistence.realm.provider

import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

class RealmProviderImpl @Inject constructor():RealmProvider {

    companion object {
        private const val REALM_NAME = "com.azhimkulov.realm"
        private const val REALM_SCHEMA_VERSION = 0
    }

    private var realmConfiguration: RealmConfiguration? = null

    override fun provide(): Realm {
        return getRealm()
    }

    private fun getRealm(): Realm {
        buildRealmConfIfNotExist()

        realmConfiguration?.let {
            return Realm.getInstance(it)
        }

        throw Exception()
    }

    private fun buildRealmConfIfNotExist() {
        if (realmConfiguration == null) {
            this.realmConfiguration = RealmConfiguration.Builder()
                .name(REALM_NAME)
                .schemaVersion(REALM_SCHEMA_VERSION.toLong())
                .deleteRealmIfMigrationNeeded()
                .build()
        }
    }
}