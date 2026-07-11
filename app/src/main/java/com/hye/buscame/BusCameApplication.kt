package com.hye.buscame

import android.app.Application
import com.hye.data.di.KakaoNativeAppKey
import com.kakao.vectormap.KakaoMapSdk
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class BusCameApplication : Application() {

    @Inject
    @KakaoNativeAppKey
    lateinit var kakaoKey: String

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        KakaoMapSdk.init(this, kakaoKey)
    }
}