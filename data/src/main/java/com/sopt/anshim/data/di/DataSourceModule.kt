package com.sopt.anshim.data.di

import com.sopt.anshim.data.datasource.local.BookLocalDataSource
import com.sopt.anshim.data.datasourceimpl.local.BookLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindDataSource(bookLocalDataSourceImpl: BookLocalDataSourceImpl): BookLocalDataSource
}