package com.hye.data.di.module

import com.hye.data.BuildConfig
import com.hye.data.di.qualifier.KakaoNativeAppKey
import com.hye.data.di.qualifier.KakaoRestApiKey
import com.hye.data.di.qualifier.SeoulBusApiKey
import com.hye.data.di.qualifier.TagoBusSttnApiKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApiKeyModule {

    @Provides
    @KakaoNativeAppKey
    fun provideKakaoNativeAppKey(): String = BuildConfig.KAKAO_NATIVE_APP_KEY

    @Provides
    @KakaoRestApiKey
    fun provideKakaoRestApiKey(): String = BuildConfig.KAKAO_REST_API_KEY

    @Provides
    @TagoBusSttnApiKey
    fun provideTagoBusSttnApiKey(): String = BuildConfig.BUS_STTN_INFO_API_KEY

    @Provides
    @SeoulBusApiKey
    fun provideSeoulBusApiKey(): String = BuildConfig.SEOUL_BUS_API_KEY
}