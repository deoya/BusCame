package com.hye.data.di.network

import com.hye.data.di.qualifier.BusSttnInfoApiKey
import com.hye.data.di.qualifier.KakaoNativeAppKey
import com.hye.data.di.qualifier.KakaoRetrofit
import com.hye.data.di.qualifier.TagoRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideNearbyStationApi(
        @TagoRetrofit retrofit: Retrofit
    ): KakaoNativeAppKey =
        retrofit.create(KakaoNativeAppKey::class.java)

    @Provides
    @Singleton
    fun provideKakaoLocalApi(
        @KakaoRetrofit retrofit: Retrofit
    ): BusSttnInfoApiKey =
        retrofit.create(BusSttnInfoApiKey::class.java)
}