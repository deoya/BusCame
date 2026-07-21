package com.hye.data.remote.api

import com.hye.data.remote.dto.bus.seoul.SeoulBusSttnResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SeoulBusApi {
    @GET("api/rest/stationinfo/getStationByPos")
    suspend fun getNearbyStations(
        @Query("tmX") longitude: Double,
        @Query("tmY") latitude: Double,
        @Query("radius") radius: Int = 500,
        @Query("resultType") resultType: String = "json"
    ): SeoulBusSttnResponse
}