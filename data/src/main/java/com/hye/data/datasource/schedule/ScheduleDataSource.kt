package com.hye.data.datasource.schedule

import com.hye.domain.model.schedule.WorkSchedule

interface ScheduleDataSource {
    suspend fun getSchedule(): WorkSchedule
    suspend fun saveSchedule(schedule: WorkSchedule)
}