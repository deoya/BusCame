package com.hye.data.repository

import com.hye.data.datasource.schedule.ScheduleDataSource
import com.hye.data.di.qualifier.IoDispatcher
import com.hye.domain.model.common.ResultWrapper
import com.hye.domain.model.schedule.WorkSchedule
import com.hye.domain.repository.ScheduleRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ScheduleRepositoryImpl @Inject constructor(
    private val dataStore: ScheduleDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ScheduleRepository {


    override suspend fun getSchedule(): ResultWrapper<WorkSchedule> {
        return withContext(ioDispatcher) {
            runCatching {
                dataStore.getSchedule()
            }.fold(
                onSuccess = { ResultWrapper.Success(it) },
                onFailure = { error ->
                    if (error is CancellationException) throw error
                    ResultWrapper.Error(error)
                }
            )
        }
    }

    override suspend fun saveSchedule(schedule: WorkSchedule): ResultWrapper<Unit> {
        return withContext(ioDispatcher) {
            runCatching {
                dataStore.saveSchedule(schedule)
            }.fold(
                onSuccess = { ResultWrapper.Success(it) },
                onFailure = { error ->
                    if (error is CancellationException) throw error
                    ResultWrapper.Error(error)
                }
            )
        }
    }
}