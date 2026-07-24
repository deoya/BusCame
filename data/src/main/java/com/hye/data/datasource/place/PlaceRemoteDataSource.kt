package com.hye.data.datasource.place

import com.hye.data.remote.dto.place.KakaoPlaceDocument

interface PlaceRemoteDataSource {
    suspend fun searchPlaces(keyword: String): List<KakaoPlaceDocument>
}