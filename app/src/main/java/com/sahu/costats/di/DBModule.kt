package com.sahu.costats.di

import android.content.Context
import androidx.room.Room
import com.sahu.appUtil.AppDatabase
import com.sahu.appUtil.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Singleton
    @Provides
    fun getDB(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
        .build()

    @Singleton
    @Provides
    fun getCountryDao(appDatabase: AppDatabase) = appDatabase.countryDao()

    @Singleton
    @Provides
    fun getProvinceDao(appDatabase: AppDatabase) = appDatabase.provinceDoa()

    @Singleton
    @Provides
    fun getHistoricDao(appDatabase: AppDatabase) = appDatabase.historicDao()

}