package com.hye.data.di.module.network

import com.hye.data.di.qualifier.KakaoOkHttp
import com.hye.data.di.qualifier.KakaoRestApiKey
import com.hye.data.di.qualifier.KakaoRetrofit
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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KakaoNetworkModule {

    @Provides
    @Singleton
    @KakaoOkHttp
    fun provideKakaoOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @KakaoRestApiKey apiKey: String
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->

                val request = chain.request()
                    .newBuilder()
                    .addHeader(
                        "Authorization",
                        "KakaoAK $apiKey"
                    )
                    .build()

                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    @KakaoRetrofit
    fun provideKakaoRetrofit(
        @KakaoOkHttp okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com/")
            .client(okHttpClient)
            .addConverterFactory(
                json.asConverterFactory(
                    "application/json".toMediaType()
                )
            )
            .build()
    }
}