package com.hye.features.route.presentation.viewmodel

import com.hye.domain.model.route.BusStop
import com.hye.domain.model.route.Place

sealed class RouteIntent {
    data object CancelSelection : RouteIntent() //시트를 닫거나 취소할 때
    data object ClickDepartureInput : RouteIntent()   // 출발지 입력창 클릭
    data object ClickArrivalInput : RouteIntent()     // 도착지 입력창 클릭
    data class MapCenterChanged(val lat: Double, val lng: Double) : RouteIntent() // 지도 드래그 멈춤
    data class UpdateSearchQuery(val query: String) : RouteIntent() // 글자 입력
    data class SelectPlace(val place: Place) : RouteIntent()        // 자동완성 목록에서 장소 클릭
    data class ClickBusStopPin(val stop: BusStop) : RouteIntent() //지도 위의 정류장 핀을 클릭했을 때의 액션
    data object ConfirmSelection : RouteIntent()      // "여기로 설정" 버튼 클릭
}