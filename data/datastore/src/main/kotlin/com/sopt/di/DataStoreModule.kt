package com.sopt.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.sopt.datastore.BookDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    private val Context.bookDataStore: DataStore<Preferences> by preferencesDataStore(name = "book.pref")

    @Singleton
    @Provides
    fun provideBookDataStore(
        @ApplicationContext context: Context
    ) : BookDataStore {
        return BookDataStore(context.bookDataStore)
    }
}