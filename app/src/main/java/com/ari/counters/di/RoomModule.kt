package com.ari.counters.di

import android.content.Context
import androidx.room.Room
import com.ari.counters.framework.database.CounterDataBase
import com.ari.counters.framework.database.dao.CounterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val COUNTER_DATABASE_NAME = "counter_database"

    @Singleton
    @Provides
    fun provideCounterDB(@ApplicationContext context: Context): CounterDataBase = Room
        .databaseBuilder(context, CounterDataBase::class.java, COUNTER_DATABASE_NAME)
        .build()

    @Singleton
    @Provides
    fun provideCounterDao(counterDataBase: CounterDataBase): CounterDao = counterDataBase.getDao()

}