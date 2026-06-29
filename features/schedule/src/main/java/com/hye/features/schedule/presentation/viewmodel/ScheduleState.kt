package com.hye.features.schedule.presentation.viewmodel

import com.hye.domain.model.WorkSchedule


data class ScheduleState(
    val schedule: WorkSchedule = WorkSchedule(),
    val isLoading: Boolean = false
)