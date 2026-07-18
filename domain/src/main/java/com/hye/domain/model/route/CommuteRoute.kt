package com.hye.domain.model.route

data class CommuteRoute(
    val departureStop: BusStop? = null,    // 타야 할 출발 정거장
    val destinationStop: BusStop? = null   // 내려야 할 도착 정거장
)