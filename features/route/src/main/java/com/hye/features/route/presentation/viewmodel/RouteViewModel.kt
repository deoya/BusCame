package com.hye.features.route.presentation.viewmodel

import android.location.Location
import androidx.lifecycle.viewModelScope
import com.hye.common.design.base.BaseViewModel
import com.hye.domain.model.common.ResultWrapper
import com.hye.domain.model.common.UiStateResult
import com.hye.domain.model.route.SelectionMode
import com.hye.domain.usecase.route.BusStopsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RouteViewModel @Inject constructor(
    private val busStopsUseCase: BusStopsUseCase
) : BaseViewModel() {
    private val _state = MutableStateFlow(RouteState())
    val state: StateFlow<RouteState> = _state.asStateFlow()

    private var searchJob: Job? = null

    // 마지막으로 API를 호출했던 좌표
    private var lastFetchedLocation: Pair<Double, Double>? = null

    fun processIntent(intent: RouteIntent) {
        when (intent) {
            is RouteIntent.ClickDepartureInput -> {
                _state.update { it.copy(selectionMode = SelectionMode.DEPARTURE) }
            }

            is RouteIntent.ClickArrivalInput -> {
                _state.update { it.copy(selectionMode = SelectionMode.ARRIVAL) }
            }

            is RouteIntent.MapCenterChanged -> {
                _state.update {
                    it.copy(
                        currentMapCenter = Pair(
                            intent.lat, intent.lng
                        ),
                        selectedBusStop = null
                    )
                }
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(400)
                    val shouldFetch = if (lastFetchedLocation != null) {
                        val results = FloatArray(1)
                        Location.distanceBetween(
                            lastFetchedLocation!!.first, lastFetchedLocation!!.second,
                            intent.lat, intent.lng,
                            results
                        )
                        results[0] >= 200f // 200미터 이상 움직였을 때만 true
                    } else {
                        true // 처음 켰을 때는 무조건 검색
                    }
                    if (shouldFetch) {
                        fetchNearbyStops(intent.lat, intent.lng)
                    }
                }
            }

            is RouteIntent.ConfirmSelection -> {
                confirmCurrentSelection()
            }

            is RouteIntent.CancelSelection -> {
                _state.update {
                    it.copy(
                        selectionMode = SelectionMode.NONE, // 모달 닫기
                        selectedBusStop = null,             // 핀 선택 초기화
                        searchQuery = "",                   // 검색어 초기화 (선택사항)
                        searchResults = UiStateResult.Idle // 검색 결과 초기화 (선택사항)
                    )
                }
            }

            is RouteIntent.UpdateSearchQuery -> {
                _state.update { it.copy(searchQuery = intent.query) }
                searchPlaces(intent.query)
            }

            is RouteIntent.SelectPlace -> {
                _state.update {
                    it.copy(
                        searchQuery = intent.place.name,
                        searchResults = UiStateResult.Idle,
                        currentMapCenter = Pair(intent.place.latitude, intent.place.longitude)
                    )
                }
                fetchNearbyStops(intent.place.latitude, intent.place.longitude)
            }

            is RouteIntent.ClickBusStopPin -> {
                _state.update { it.copy(selectedBusStop = intent.stop) }
            }
        }
    }

    private fun searchPlaces(query: String) {
        if (query.isBlank()) {
            _state.update { it.copy(searchResults = UiStateResult.Idle) }
            return
        }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            _state.update { it.copy(searchResults = UiStateResult.Loading) }

            when (val result = busStopsUseCase.searchPlacesUseCase(query)) {
                is ResultWrapper.Success -> _state.update {
                    Timber.d("🔍 [검색 성공] 데이터 개수: ${result.data.size}개 (검색어: $query)")
                    it.copy(
                        searchResults = UiStateResult.Success(
                            result.data
                        )
                    )
                }

                is ResultWrapper.Error -> _state.update {
                    Timber.e(result.exception, "💥 [검색 실패] 원인을 확인하세요!")
                    it.copy(
                        searchResults = UiStateResult.Error(
                            result.exception
                        )
                    )
                }
            }
        }
    }


    private fun confirmCurrentSelection() {
        val currentState = _state.value
        val mode = currentState.selectionMode
        val selectedStop = currentState.selectedBusStop ?: return

        if (mode == SelectionMode.NONE) return

        _state.update { state ->
            val updatedState = when (mode) {
                SelectionMode.DEPARTURE -> state.copy(departureStop = selectedStop)
                SelectionMode.ARRIVAL -> state.copy(arrivalStop = selectedStop)
                else -> state
            }
            updatedState.copy(
                selectionMode = SelectionMode.NONE,
                selectedBusStop = null
            )
        }
    }

    private fun fetchNearbyStops(lat: Double, lng: Double) {
        viewModelScope.launch {
            _state.update { it.copy(nearbyStopsState = UiStateResult.Loading) }

            when (val result = busStopsUseCase.getNearbyBusStopsUseCase(lat, lng)) {
                is ResultWrapper.Success -> {
                    Timber.d("🔍 [검색 성공] 데이터 개수: ${result.data.size}개")
                    lastFetchedLocation = lat to lng
                    _state.update { it.copy(nearbyStopsState = UiStateResult.Success(result.data)) }
                }

                is ResultWrapper.Error -> {
                    Timber.e(result.exception, "💥 [검색 실패] 원인을 확인하세요!")
                    _state.update { it.copy(nearbyStopsState = UiStateResult.Error(result.exception)) }
                }
            }
        }
    }

}