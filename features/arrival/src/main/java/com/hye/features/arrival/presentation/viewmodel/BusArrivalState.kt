package com.hye.features.arrival.presentation.viewmodel

data class BusArrivalState(
    val isLoading: Boolean = true,
//    val direction: CommuteDirection = CommuteDirection.TO_WORK,
//    val isBoarded: Boolean = false,
//    val routeConfig: RouteConfig? = null,
//    val busInfo: BusArrivalInfo? = null,
    val errorMessage: String? = null
)