package com.hye.domain.repository

import com.hye.domain.model.common.ResultWrapper
import com.hye.domain.model.schedule.WorkSchedule

interface ScheduleRepository {
    suspend fun getSchedule(): ResultWrapper<WorkSchedule>

    suspend fun saveSchedule(schedule: WorkSchedule): ResultWrapper<Unit>
}