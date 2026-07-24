package com.hye.data.datasource.place

import com.hye.data.remote.api.KakaoLocalApi
import com.hye.data.remote.dto.place.KakaoPlaceDocument
import javax.inject.Inject

class PlaceRemoteDataSourceImpl @Inject constructor(
    private val api: KakaoLocalApi
) : PlaceRemoteDataSource {
    override suspend fun searchPlaces(keyword: String): List<KakaoPlaceDocument> =
        api.searchPlaces(keyword).documents
}