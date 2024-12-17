package com.sopt.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sopt.model.book.Book
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class BookDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun saveBookTemporary(book: Book) {
        dataStore.edit { preferences ->
            val bookJson = Json.encodeToString(book)
            preferences[KEY_BOOK] = bookJson
        }
    }

    suspend fun getBookTemporary(): Book? {
        return dataStore.data.map { preferences ->
            val bookJson = preferences[KEY_BOOK] ?: return@map null
            Json.decodeFromString<Book>(bookJson)
        }.firstOrNull()
    }

    private companion object {
        val KEY_BOOK = stringPreferencesKey("key_book")
    }
}