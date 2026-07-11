package com.hye.data.di.qualifier

import com.hye.data.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class KakaoNativeAppKey

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BusSttnInfoApiKey

@Module
@InstallIn(SingletonComponent::class)
object ApiKeyModule {

    @Provides
    @KakaoNativeAppKey
    fun provideKakaoNativeAppKey(): String = BuildConfig.KAKAO_NATIVE_APP_KEY

    @Provides
    @BusSttnInfoApiKey
    fun provideBusSttnInfoApiKey(): String = BuildConfig.BUS_STTN_INFO_API_KEY
}