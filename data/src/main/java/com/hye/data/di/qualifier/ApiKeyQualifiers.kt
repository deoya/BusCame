package com.hye.data.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class KakaoNativeAppKey

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class KakaoRestApiKey

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TagoBusSttnApiKey

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SeoulBusApiKey