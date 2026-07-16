package com.hye.data.di.module.network

import com.hye.data.BuildConfig
import com.hye.data.di.qualifier.TagoOkHttp
import com.hye.data.di.qualifier.TagoRetrofit
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

/*
* BASE_URL,Logger, Timeout 관리
* */
@Module
@InstallIn(SingletonComponent::class)
object TagoNetworkModule {
    private const val BASE_URL = "https://apis.data.go.kr/"

    @Provides
    @Singleton
    @TagoOkHttp
    fun provideTagoOkHttpClient(): OkHttpClient {

        val logger = HttpLoggingInterceptor {
            Timber.tag("OkHttp").d(it)
        }.apply {
            level =
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    @TagoRetrofit
    fun provideTagoRetrofit(
        @TagoOkHttp okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                json.asConverterFactory(
                    "application/json".toMediaType()
                )
            )
            .build()
    }
}