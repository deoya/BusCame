package com.hye.data.di.module.datasource

import com.hye.data.datasource.place.PlaceRemoteDataSource
import com.hye.data.datasource.place.PlaceRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PlaceDataSourceModule {
    @Provides
    @Singleton
    abstract fun bindPlaceDataSource(
        impl: PlaceRemoteDataSourceImpl
    ): PlaceRemoteDataSource
}