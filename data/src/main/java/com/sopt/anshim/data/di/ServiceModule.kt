package com.sopt.anshim.data.di

import com.sopt.anshim.data.service.BookService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {

    @Provides
    @Singleton
    fun provideBookService(
        retrofit: Retrofit
    ): BookService = retrofit.create(BookService::class.java)

}