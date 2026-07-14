package com.hye.features.route.presentation.viewmodel

import com.hye.domain.model.common.UiStateResult
import com.hye.domain.model.route.BusStop
import com.hye.domain.model.route.Place
import com.hye.domain.model.route.SelectionMode

data class RouteState(
    val nearbyStopsState: UiStateResult<List<BusStop>> = UiStateResult.Idle,

    val departureStop: BusStop? = null,
    val arrivalStop: BusStop? = null,
    val selectionMode: SelectionMode = SelectionMode.NONE,
    val currentMapCenter: Pair<Double, Double>? = null, // (위도, 경도)

    val isBottomSheetOpen: Boolean = false,
    val searchQuery: String = "", // 검색창 입력 텍스트
    val searchResults: UiStateResult<List<Place>> = UiStateResult.Idle // 자동완성 검색 결과 리스트
)