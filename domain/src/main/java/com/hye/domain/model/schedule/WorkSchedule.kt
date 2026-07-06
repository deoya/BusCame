package com.hye.domain.model.schedule

data class WorkSchedule(
    val activeDays: Set<DayOfWeek> = setOf(
        DayOfWeek.MON,
        DayOfWeek.TUE,
        DayOfWeek.WED,
        DayOfWeek.THU,
        DayOfWeek.FRI
    ),
    val commuteHour: Int = 9, val commuteMinute: Int = 0,
    val offworkHour: Int = 18, val offworkMinute: Int = 0
)