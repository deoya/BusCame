package com.hye.data.di.module.datasource

import com.hye.data.datasource.BusRemoteDataSource
import com.hye.data.datasource.seoul.SeoulBusRemoteDataSourceImpl
import com.hye.data.datasource.tago.TagoBusRemoteDataSourceImpl
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