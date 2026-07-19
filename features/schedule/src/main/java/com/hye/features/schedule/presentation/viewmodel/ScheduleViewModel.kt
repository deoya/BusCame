package com.hye.features.schedule.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.hye.common.design.base.BaseViewModel
import com.hye.domain.model.common.ResultWrapper
import com.hye.domain.model.common.UiStateResult
import com.hye.domain.model.schedule.DayOfWeek
import com.hye.domain.usecase.schedule.ScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val scheduleUseCase: ScheduleUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow(ScheduleState())
    val state: StateFlow<ScheduleState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ScheduleEffect>()
    val effect: SharedFlow<ScheduleEffect> = _effect.asSharedFlow()

    private var saveJob: Job? = null

    init {
        loadSavedSchedule()
    }

    fun processIntent(intent: ScheduleIntent) {
        when (intent) {
            is ScheduleIntent.ToggleDay -> toggleDay(intent.day)
            is ScheduleIntent.ClickCommuteTime -> sendEffect(ScheduleEffect.ShowCommuteTimePicker)
            is ScheduleIntent.ClickOffworkTime -> sendEffect(ScheduleEffect.ShowOffworkTimePicker)
            is ScheduleIntent.UpdateCommuteTime -> updateCommuteTime(intent.hour, intent.minute)
            is ScheduleIntent.UpdateOffworkTime -> updateOffworkTime(intent.hour, intent.minute)
        }
    }

    private fun loadSavedSchedule() {
        viewModelScope.launch(commonCeh) {
            _state.update { it.copy(scheduleLoadState = UiStateResult.Loading) }

            when (val result = scheduleUseCase.getScheduleUseCase()) {
                is ResultWrapper.Success -> {
                    _state.update { currentState ->
                        currentState.copy(
                            schedule = result.data,
                            scheduleLoadState = UiStateResult.Success(result.data)
                        )
                    }
                }

                is ResultWrapper.Error -> {
                    _state.update { currentState ->
                        currentState.copy(
                            scheduleLoadState = UiStateResult.Error(result.exception)
                        )
                    }
                }
            }
        }
    }

    private fun toggleDay(day: DayOfWeek) {
        _state.update { currentState ->
            val currentDays = currentState.schedule.activeDays
            val newDays = if (currentDays.contains(day)) currentDays - day else currentDays + day
            currentState.copy(schedule = currentState.schedule.copy(activeDays = newDays))
        }
        saveScheduleAutomatically()
    }

    private fun updateCommuteTime(hour: Int, minute: Int) {
        _state.update {
            it.copy(schedule = it.schedule.copy(commuteHour = hour, commuteMinute = minute))
        }
        saveScheduleAutomatically()
    }

    private fun updateOffworkTime(hour: Int, minute: Int) {
        _state.update {
            it.copy(schedule = it.schedule.copy(offworkHour = hour, offworkMinute = minute))
        }
        saveScheduleAutomatically()
    }

    private fun saveScheduleAutomatically() {
        saveJob?.cancel() // 0.3초 안에 다시 요청이 오면 기존 저장 작업 취소 (연타 방어)

        saveJob = viewModelScope.launch(commonCeh) {
            delay(300) // 300ms 대기 후 최종 1회만 디스크 쓰기 실행
            scheduleUseCase.saveScheduleUseCase(_state.value.schedule)
            Timber.d("💾 [ScheduleViewModel] 일정 자동 저장 완료: ${_state.value.schedule}")
        }
    }

    private fun sendEffect(effect: ScheduleEffect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}