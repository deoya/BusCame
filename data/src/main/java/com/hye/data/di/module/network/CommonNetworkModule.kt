package com.hye.data.di.module.network

import com.hye.data.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonNetworkModule {

    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true // DTO에 정의되지 않은 새 변수가 내려와도 무시
            coerceInputValues = true // 예상치 못한 null이나 타입 불일치 시 디폴트 값 강제 치환
            isLenient = true         // 형식이 완벽한 JSON 포맷이 아니더라도 유연하게 파싱
        }
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message ->
            Timber.tag("NetworkApi").d(message)
        }.apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE // 릴리즈 시 무거운 String 변환 작업 원천 차단
            }
        }
    }
}