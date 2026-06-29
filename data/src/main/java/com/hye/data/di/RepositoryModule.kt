package com.hye.data.di

import com.hye.data.repository.ScheduleRepositoryImpl
import com.hye.domain.repository.ScheduleRepository
import dagger.Binds
import javax.inject.Singleton

abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindScheduleRepository(
        scheduleRepositoryImpl: ScheduleRepositoryImpl
    ): ScheduleRepository
}