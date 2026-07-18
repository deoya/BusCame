package com.hye.domain.usecase.schedule

import com.hye.domain.model.common.ResultWrapper
import com.hye.domain.model.schedule.WorkSchedule
import com.hye.domain.repository.ScheduleRepository
import javax.inject.Inject

class GetScheduleUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) {
    suspend operator fun invoke(): ResultWrapper<WorkSchedule> {
        return scheduleRepository.getSchedule()
    }
}