package com.hye.features.schedule.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.hye.common.design.base.BaseViewModel
import com.hye.domain.model.DayOfWeek
import com.hye.domain.usecase.schedule.ScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val scheduleUseCase: ScheduleUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow(ScheduleState())
    val state: StateFlow<ScheduleState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ScheduleEffect>()
    val effect: SharedFlow<ScheduleEffect> = _effect.asSharedFlow()

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
            is ScheduleIntent.SaveSchedule -> saveSchedule()
        }
    }

    private fun loadSavedSchedule() {
        viewModelScope.launch(commonCeh) {
            _state.update { it.copy(isLoading = true) }

            scheduleUseCase.getScheduleUseCase()
                .catch { throwable ->
                    _state.update { it.copy(isLoading = false) }
                    throw throwable
                }
                .collect { savedSchedule ->
                    _state.update { it.copy(schedule = savedSchedule, isLoading = false) }
                }
        }
    }

    private fun toggleDay(day: DayOfWeek) {
        _state.update { currentState ->
            val currentDays = currentState.schedule.activeDays
            val newDays = if (currentDays.contains(day)) currentDays - day else currentDays + day
            currentState.copy(schedule = currentState.schedule.copy(activeDays = newDays))
        }
    }

    private fun updateCommuteTime(hour: Int, minute: Int) {
        _state.update {
            it.copy(schedule = it.schedule.copy(commuteHour = hour, commuteMinute = minute))
        }
    }

    private fun updateOffworkTime(hour: Int, minute: Int) {
        _state.update {
            it.copy(schedule = it.schedule.copy(offworkHour = hour, offworkMinute = minute))
        }
    }

    private fun saveSchedule() {
        viewModelScope.launch(commonCeh) {
            _state.update { it.copy(isLoading = true) }

            scheduleUseCase.saveScheduleUseCase(_state.value.schedule)

            _state.update { it.copy(isLoading = false) }

            showToast("기기에 근무 일정이 안전하게 저장되었습니다.")
        }
    }

    private fun sendEffect(effect: ScheduleEffect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}