package com.sopt.anshim.data.di

import android.content.Context
import androidx.room.Room
import com.sopt.anshim.data.database.BookDatabase
import com.sopt.anshim.data.database.dao.BookDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun provideBookDatabase(@ApplicationContext appContext: Context): BookDatabase =
        Room.databaseBuilder(appContext, BookDatabase::class.java, "book_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideBookDao(db: BookDatabase): BookDao = db.bookDao()
}