package com.sopt.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sopt.model.book.Book
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

    private companion object {
        val KEY_BOOK = stringPreferencesKey("key_book")
    }
}