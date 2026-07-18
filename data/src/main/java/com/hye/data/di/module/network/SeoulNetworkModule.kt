package com.hye.data.di.module.network

import com.hye.data.BuildConfig
import com.hye.data.di.qualifier.SeoulOkHttp
import com.hye.data.di.qualifier.SeoulRetrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SeoulNetworkModule {

    @Provides
    @Singleton
    @SeoulOkHttp
    fun provideSeoulOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor { message -> Timber.tag("SeoulApi").d(message) }
            .apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }

        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    @SeoulRetrofit
    fun provideSeoulRetrofit(
        @SeoulOkHttp okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://ws.bus.go.kr/")
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
}