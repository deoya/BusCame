package com.hye.features.route.presentation.viewmodel

import com.hye.domain.model.common.UiStateResult
import com.hye.domain.model.route.BusStop
import com.hye.domain.model.route.Place
import com.hye.domain.model.route.SelectionMode

data class RouteState(
    val departureStop: BusStop? = null,  // 출발 정거장
    val arrivalStop: BusStop? = null, // 도착 정거장
    val isBottomSheetOpen: Boolean = false, // 맵 선택 창 상태
    val selectionMode: SelectionMode = SelectionMode.NONE, // 지도 상태

    val currentMapCenter: Pair<Double, Double>? = null, // (위도, 경도)
    val searchQuery: String = "", // 검색창 입력 텍스트
    val searchResults: UiStateResult<List<Place>> = UiStateResult.Idle, // 자동완성 검색 결과 리스트

    val nearbyStopsState: UiStateResult<List<BusStop>> = UiStateResult.Idle, // 인근 버스 정류장 리스트
    val selectedBusStop: BusStop? = null,// 선택 버스 정거장
)