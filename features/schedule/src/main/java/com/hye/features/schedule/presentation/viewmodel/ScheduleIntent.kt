package com.hye.features.schedule.presentation.viewmodel

import com.hye.domain.model.schedule.DayOfWeek


sealed class ScheduleIntent {
    data class ToggleDay(val day: DayOfWeek) : ScheduleIntent()
    object ClickCommuteTime : ScheduleIntent()
    object ClickOffworkTime : ScheduleIntent()

    // 시간 업데이트 용
    data class UpdateCommuteTime(val hour: Int, val minute: Int) : ScheduleIntent()
    data class UpdateOffworkTime(val hour: Int, val minute: Int) : ScheduleIntent()
    object SaveSchedule : ScheduleIntent()
}