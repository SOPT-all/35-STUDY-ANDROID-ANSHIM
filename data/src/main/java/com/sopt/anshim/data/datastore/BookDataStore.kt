package com.sopt.anshim.data.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sopt.anshim.data.model.BookData
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class BookDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    suspend fun saveBookTemporarily(bookData: BookData) {
        dataStore.edit { preferences ->
            val jsonBook = Json.encodeToString(bookData)
            preferences[KEY_BOOK_DATASTORE] = jsonBook
        }
    }

    suspend fun getBookTemporarily(): BookData? =
        dataStore.data.map { preferences ->
            val jsonBook = preferences[KEY_BOOK_DATASTORE] ?: return@map null
            Json.decodeFromString<BookData>(jsonBook)
        }.lastOrNull()

    private companion object {
        val KEY_BOOK_DATASTORE = stringPreferencesKey("key_book_datastore")
    }
}
