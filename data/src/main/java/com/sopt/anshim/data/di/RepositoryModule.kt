package com.sopt.anshim.data.di

import com.sopt.anshim.data.repository.BookRepositoryImpl
import com.sopt.anshim.domain.repository.BookRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {
    @Binds
    fun bindBookRepository(bookRepositoryImpl: BookRepositoryImpl): BookRepository
}