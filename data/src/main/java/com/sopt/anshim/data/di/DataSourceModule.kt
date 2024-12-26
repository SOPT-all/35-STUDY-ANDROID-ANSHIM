package com.sopt.anshim.data.di

import com.sopt.anshim.data.database.dao.BookDao
import com.sopt.anshim.data.datasource.local.BookLocalDataSource
import com.sopt.anshim.data.datasource.remote.BookRemoteDataSource
import com.sopt.anshim.data.service.BookService
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
    fun provideBookLocalDataSource(
        bookDao: BookDao
    ): BookLocalDataSource = BookLocalDataSource(bookDao)

    @Singleton
    @Provides
    fun provideBookRemoteDataSource(
        bookService: BookService
    ): BookRemoteDataSource = BookRemoteDataSource(bookService)
}