package com.hye.domain.usecase.schedule

import com.hye.domain.model.WorkSchedule
import com.hye.domain.repository.ScheduleRepository
import javax.inject.Inject

class SaveScheduleUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) {
    suspend operator fun invoke(schedule: WorkSchedule) {
        scheduleRepository.saveSchedule(schedule)
    }
}