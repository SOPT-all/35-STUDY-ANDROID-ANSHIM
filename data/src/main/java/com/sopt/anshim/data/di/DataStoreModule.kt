package com.sopt.anshim.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.sopt.anshim.data.datastore.BookDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "book.ref")

    @Singleton
    @Provides
    fun providesDataStore(
        @ApplicationContext context: Context,
    ): BookDataStore = BookDataStore(context.dataStore)
}