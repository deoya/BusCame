package com.hye.domain.model.arrival

import com.hye.domain.model.route.BusStop

data class RouteConfig(
    val departure: BusStop?,
    val arrival: BusStop?
)