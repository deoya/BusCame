package com.hye.data.di.module.datasource

import com.hye.data.datasource.bus.BusRemoteDataSource
import com.hye.data.datasource.bus.SeoulBusRemoteDataSourceImpl
import com.hye.data.datasource.bus.TagoBusRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class BusDataSourceModule {
    @Binds
    @IntoSet
    abstract fun bindTagoDataSource(
        impl: TagoBusRemoteDataSourceImpl
    ): BusRemoteDataSource

    @Binds
    @IntoSet
    abstract fun bindSeoulDataSource(
        impl: SeoulBusRemoteDataSourceImpl
    ): BusRemoteDataSource
}