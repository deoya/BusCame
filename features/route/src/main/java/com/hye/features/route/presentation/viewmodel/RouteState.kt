package com.hye.features.route.presentation.viewmodel

import com.hye.domain.model.route.BusStop
import com.hye.domain.model.route.SelectionMode

data class RouteState(
    val departureStop: BusStop? = null,
    val arrivalStop: BusStop? = null,
    val selectionMode: SelectionMode = SelectionMode.NONE,
    val currentMapCenter: Pair<Double, Double>? = null, // (위도, 경도)

    val isBottomSheetOpen: Boolean = false,
)