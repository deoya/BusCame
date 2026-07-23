package com.hye.data.di.module.datasource

import com.hye.data.datasource.route.RouteLocalDataSource
import com.hye.data.datasource.route.RouteLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RouteDataSourceModule {

    @Provides
    @Singleton
    abstract fun bindRouteDataSource(
        impl: RouteLocalDataSourceImpl
    ): RouteLocalDataSource
}