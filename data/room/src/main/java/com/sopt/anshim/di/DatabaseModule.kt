package com.sopt.anshim.di

import android.content.Context
import androidx.room.Room
import com.sopt.anshim.room.BookDao
import com.sopt.anshim.room.BookDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BookDatabase =
        Room.databaseBuilder(context, BookDatabase::class.java, "book_database").build()

    @Provides
    fun provideBookDao(database: BookDatabase): BookDao =
        database.bookDao()
}

