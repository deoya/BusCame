package com.hye.features.schedule.presentation.viewmodel

sealed interface ScheduleEffect {
    object ShowCommuteTimePicker : ScheduleEffect
    object ShowOffworkTimePicker : ScheduleEffect
}