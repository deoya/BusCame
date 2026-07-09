package com.hye.features.route.presentation.viewmodel

import com.hye.common.design.base.BaseViewModel
import com.hye.domain.model.route.SelectionMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RouteViewModel @Inject constructor(

) : BaseViewModel() {
    private val _state = MutableStateFlow(RouteState())
    val state: StateFlow<RouteState> = _state.asStateFlow()


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
            }

            is RouteIntent.ConfirmSelection -> {
                confirmCurrentSelection()
            }

            is RouteIntent.CloseMapBottomSheet -> {
                _state.update { it.copy(selectionMode = SelectionMode.NONE) }
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
                selectionMode = SelectionMode.NONE // 선택 완료 후 지도 모드 종료
            )
        }
    }
}