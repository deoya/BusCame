package com.hye.data.di.module.network

import com.hye.data.di.qualifier.SeoulBusApiKey
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
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SeoulNetworkModule {

    private const val SEOUL_BASE_URL = "http://ws.bus.go.kr/"

    @Provides
    @Singleton
    @SeoulOkHttp
    fun provideSeoulOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @SeoulBusApiKey apiKey: String
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val original = chain.request()
                val url = original.url.newBuilder()
                    .addQueryParameter("serviceKey", apiKey)
                    .addQueryParameter("resultType", "json") // 응답 타입 강제
                    .build()
                chain.proceed(original.newBuilder().url(url).build())
            }
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
            .baseUrl(SEOUL_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
}