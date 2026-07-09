package com.hye.features.route.presentation.viewmodel

sealed class RouteIntent {
    data object ClickDepartureInput : RouteIntent()   // 출발지 입력창 클릭
    data object ClickArrivalInput : RouteIntent()     // 도착지 입력창 클릭
    data class MapCenterChanged(val lat: Double, val lng: Double) : RouteIntent() // 지도 드래그 멈춤
    data object ConfirmSelection : RouteIntent()      // "여기로 설정" 버튼 클릭

    data object CloseMapBottomSheet : RouteIntent() //바텀 시트를 닫거나 취소할 때
}