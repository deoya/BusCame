package com.hye.data.di.module

import com.hye.data.repository.BusRepositoryImpl
import com.hye.data.repository.PlaceRepositoryImpl
import com.hye.data.repository.ScheduleRepositoryImpl
import com.hye.domain.repository.BusRepository
import com.hye.domain.repository.PlaceRepository
import com.hye.domain.repository.ScheduleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindScheduleRepository(
        scheduleRepositoryImpl: ScheduleRepositoryImpl
    ): ScheduleRepository

    @Binds
    @Singleton
    abstract fun bindBusRepository(
        busRepositoryImpl: BusRepositoryImpl
    ): BusRepository

    @Binds
    @Singleton
    abstract fun bindPlaceRepository(
        placeRepositoryImpl: PlaceRepositoryImpl
    ): PlaceRepository

}