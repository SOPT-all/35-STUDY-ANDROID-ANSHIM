package com.sopt.anshim.data.di

import com.sopt.anshim.data.database.dao.BookDao
import com.sopt.anshim.data.datasource.local.BookLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceModule {

    @Singleton
    @Provides
    fun provideBookLocalDataStore(
        bookDao: BookDao
    ): BookLocalDataSource = BookLocalDataSource(bookDao)
}