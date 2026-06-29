package com.hye.domain.repository

import com.hye.domain.model.WorkSchedule
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {
    fun getSchedule(): Flow<WorkSchedule>

    suspend fun saveSchedule(schedule: WorkSchedule)
}