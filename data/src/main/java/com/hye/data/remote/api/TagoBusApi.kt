package com.hye.data.remote.api

import com.hye.data.remote.dto.station.bus.tago.TagoBusSttnWrapperResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TagoBusApi {
    @GET("1613000/BusSttnInfoInqireService/getCrdntPrxmtSttnList")
    suspend fun getNearbyStations(
        @Query("serviceKey") serviceKey: String,
        @Query("gpsLati") latitude: Double,
        @Query("gpsLong") longitude: Double,
        @Query("_type") type: String = "json",
        @Query("numOfRows") numOfRows: Int = 10,
        @Query("pageNo") pageNo: Int = 1
    ): TagoBusSttnWrapperResponse
}