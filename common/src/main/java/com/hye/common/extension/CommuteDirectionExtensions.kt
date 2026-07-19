package com.hye.common.extension

import com.hye.domain.model.arrival.CommuteDirection

val CommuteDirection.label: String
    get() = when (this) {
        CommuteDirection.TO_WORK -> "출근"
        CommuteDirection.TO_HOME -> "퇴근"
    }