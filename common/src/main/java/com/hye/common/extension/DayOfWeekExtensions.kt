package com.hye.common.extension

import com.hye.domain.model.schedule.DayOfWeek

val DayOfWeek.label: String
    get() = when (this) {
        DayOfWeek.MON -> "월"
        DayOfWeek.TUE -> "화"
        DayOfWeek.WED -> "수"
        DayOfWeek.THU -> "목"
        DayOfWeek.FRI -> "금"
        DayOfWeek.SAT -> "토"
        DayOfWeek.SUN -> "일"
    }