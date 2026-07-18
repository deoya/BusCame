package com.hye.features.schedule.presentation.viewmodel

import com.hye.domain.model.common.UiStateResult
import com.hye.domain.model.schedule.WorkSchedule


data class ScheduleState(
    val schedule: WorkSchedule = WorkSchedule(),
    val scheduleLoadState: UiStateResult<WorkSchedule> = UiStateResult.Idle,
    val isSaving: Boolean = false
)