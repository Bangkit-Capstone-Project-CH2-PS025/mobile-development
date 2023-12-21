package com.itinergo.utils

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatastoreManager(@ApplicationContext val context: Context) {

    val getName: Flow<String> = context.dataStore.data.map {
        it[NAME_KEY] ?: ""
    }
    val getId: Flow<Int> = context.dataStore.data.map {
        it[ID_KEY] ?: 0
    }
    val getToken: Flow<String> = context.dataStore.data.map {
        it[TOKEN_KEY] ?: ""
    }


    val getIsLogin: Flow<Boolean> = context.dataStore.data.map {
        it[IS_LOGIN_KEY] ?: false
    }

    suspend fun saveName(name: String) {
        context.dataStore.edit {
            it[NAME_KEY] = name
        }
    }

    suspend fun saveId(id: Int) {
        context.dataStore.edit {
            it[ID_KEY] = id
        }
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit {
            it[TOKEN_KEY] = token
        }
    }


    suspend fun saveIsLoginStatus(paramIsLogin: Boolean) {
        context.dataStore.edit {
            it[IS_LOGIN_KEY] = paramIsLogin
        }
    }

    suspend fun removeIsLoginStatus() {
        context.dataStore.edit {
            it.remove(IS_LOGIN_KEY)
        }
    }

    suspend fun removeToken() {
        context.dataStore.edit {
            it.remove(TOKEN_KEY)
        }
    }

    suspend fun removeName() {
        context.dataStore.edit {
            it.remove(NAME_KEY)
        }
    }
    suspend fun removeId() {
        context.dataStore.edit {
            it.remove(ID_KEY)
        }
    }


    companion object {
        private const val DATASTORE_NAME = "preferences"
        private val ID_KEY = intPreferencesKey("id_key")
        private val NAME_KEY = stringPreferencesKey("name_key")
        private val TOKEN_KEY = stringPreferencesKey("token_key")
        private val IS_LOGIN_KEY = booleanPreferencesKey("is_login_key")
        private val Context.dataStore by preferencesDataStore(
            name = DATASTORE_NAME
        )
    }
}