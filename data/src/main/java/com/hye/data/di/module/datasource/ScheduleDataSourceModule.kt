package com.hye.data.di.module.datasource

import com.hye.data.datasource.schedule.ScheduleDataSource
import com.hye.data.datasource.schedule.ScheduleDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ScheduleDataSourceModule {
    @Provides
    @Singleton
    abstract fun bindScheduleDataSource(
        impl: ScheduleDataSourceImpl
    ): ScheduleDataSource
}