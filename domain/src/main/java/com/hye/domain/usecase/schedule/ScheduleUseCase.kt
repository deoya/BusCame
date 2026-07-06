package com.hye.domain.usecase.schedule

import javax.inject.Inject

data class ScheduleUseCase @Inject constructor(
    val getScheduleUseCase: GetScheduleUseCase,
    val saveScheduleUseCase: SaveScheduleUseCase
)
