package com.hye.features.route.presentation.viewmodel

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

    fun processIntent(intent: RouteIntent) {
        when (intent) {
            is RouteIntent.ClickDepartureInput -> {
                _state.update { it.copy(selectionMode = SelectionMode.DEPARTURE) }
            }

            is RouteIntent.ClickArrivalInput -> {
                _state.update { it.copy(selectionMode = SelectionMode.ARRIVAL) }
            }

            is RouteIntent.MapCenterChanged -> {
                _state.update { it.copy(currentMapCenter = Pair(intent.lat, intent.lng)) }
                fetchNearbyStops(intent.lat, intent.lng)
            }

            is RouteIntent.ConfirmSelection -> {
                confirmCurrentSelection()
            }

            is RouteIntent.CloseMapBottomSheet -> {
                _state.update { it.copy(selectionMode = SelectionMode.NONE) }
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
        val center = currentState.currentMapCenter ?: return
        val mode = currentState.selectionMode

        if (mode == SelectionMode.NONE) return

        Timber.d("📍 [ViewModel] $mode 선택됨! 좌표: ${center.first}, ${center.second}")

        // TODO: 여기서 실제 공공데이터 API를 호출하여 위경도를 BusStop 모델로 변환
        _state.update {
            it.copy(
                selectionMode = SelectionMode.NONE
            )
        }
    }

    private fun fetchNearbyStops(lat: Double, lng: Double) {
        viewModelScope.launch {
            _state.update { it.copy(nearbyStopsState = UiStateResult.Loading) }

            when (val result = busStopsUseCase.getNearbyBusStopsUseCase(lat, lng)) {
                is ResultWrapper.Success -> {
                    _state.update { it.copy(nearbyStopsState = UiStateResult.Success(result.data)) }
                }

                is ResultWrapper.Error -> {
                    _state.update { it.copy(nearbyStopsState = UiStateResult.Error(result.exception)) }
                }
            }
        }
    }

}