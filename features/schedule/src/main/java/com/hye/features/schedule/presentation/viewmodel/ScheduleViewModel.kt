package com.hye.features.schedule.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.hye.common.design.base.BaseViewModel
import com.hye.domain.model.DayOfWeek
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ScheduleViewModel : BaseViewModel() {

    private val _state = MutableStateFlow(ScheduleState())
    val state: StateFlow<ScheduleState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ScheduleEffect>()
    val effect: SharedFlow<ScheduleEffect> = _effect.asSharedFlow()

    fun processIntent(intent: ScheduleIntent) {
        when (intent) {
            is ScheduleIntent.ToggleDay -> toggleDay(intent.day)
            ScheduleIntent.ClickCommuteTime -> sendEffect(ScheduleEffect.ShowCommuteTimePicker)
            ScheduleIntent.ClickOffworkTime -> sendEffect(ScheduleEffect.ShowOffworkTimePicker)
            is ScheduleIntent.UpdateCommuteTime -> updateCommuteTime(intent.hour, intent.minute)
            is ScheduleIntent.UpdateOffworkTime -> updateOffworkTime(intent.hour, intent.minute)
            ScheduleIntent.SaveSchedule -> saveSchedule()
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

            // TODO: DataStore에 저장하는 로직 (나중에 여기에 구현)

            _state.update { it.copy(isLoading = false) }

            showToast("근무 일정이 저장되었습니다.")
        }
    }

    private fun sendEffect(effect: ScheduleEffect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}