package com.hye.data.remote.api

import com.hye.data.remote.dto.place.KakaoPlaceResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoLocalApi {
    @GET("v2/local/search/keyword.json")
    suspend fun searchPlaces(
        @Query("query") keyword: String, // 사용자가 입력한 검색어 (예: "강남역")
        @Query("size") size: Int = 15    // 자동완성에 띄울 개수
    ): KakaoPlaceResponse
}