package com.hye.data.di.network

import com.hye.data.di.qualifier.KakaoRetrofit
import com.hye.data.di.qualifier.TagoRetrofit
import com.hye.data.remote.api.KakaoLocalApi
import com.hye.data.remote.api.TagoBusApi
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
    fun provideKakaoLocalApi(
        @KakaoRetrofit retrofit: Retrofit
    ): KakaoLocalApi =
        retrofit.create(KakaoLocalApi::class.java)

    @Provides
    @Singleton
    fun provideTagoBusApi(
        @TagoRetrofit retrofit: Retrofit
    ): TagoBusApi =
        retrofit.create(TagoBusApi::class.java)
}