package com.hye.domain.usecase.schedule

import com.hye.domain.model.WorkSchedule
import com.hye.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetScheduleUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) {
    operator fun invoke(): Flow<WorkSchedule> {
        return scheduleRepository.getSchedule()
    }
}