package com.hye.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.hye.domain.model.DayOfWeek
import com.hye.domain.model.WorkSchedule
import com.hye.domain.repository.ScheduleRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "schedule_prefs")

class ScheduleRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ScheduleRepository {

    private object Keys {
        val ACTIVE_DAYS = stringSetPreferencesKey("active_days")
        val COMMUTE_HOUR = intPreferencesKey("commute_hour")
        val COMMUTE_MINUTE = intPreferencesKey("commute_minute")
        val OFFWORK_HOUR = intPreferencesKey("offwork_hour")
        val OFFWORK_MINUTE = intPreferencesKey("offwork_minute")
    }

    private val dataStore = context.dataStore

    override fun getSchedule(): Flow<WorkSchedule> {
        return dataStore.data.map { preferences ->
            val savedDaysStr = preferences[Keys.ACTIVE_DAYS] ?: emptySet()
            val activeDays = savedDaysStr.mapNotNull { dayString ->
                runCatching { DayOfWeek.valueOf(dayString) }.getOrNull()
            }.toSet()

            WorkSchedule(
                activeDays = activeDays,
                commuteHour = preferences[Keys.COMMUTE_HOUR] ?: 9,
                commuteMinute = preferences[Keys.COMMUTE_MINUTE] ?: 0,
                offworkHour = preferences[Keys.OFFWORK_HOUR] ?: 18,
                offworkMinute = preferences[Keys.OFFWORK_MINUTE] ?: 0
            )
        }
    }

    override suspend fun saveSchedule(schedule: WorkSchedule) {
        dataStore.edit { preferences ->
            preferences[Keys.ACTIVE_DAYS] = schedule.activeDays.map { it.name }.toSet()

            preferences[Keys.COMMUTE_HOUR] = schedule.commuteHour
            preferences[Keys.COMMUTE_MINUTE] = schedule.commuteMinute
            preferences[Keys.OFFWORK_HOUR] = schedule.offworkHour
            preferences[Keys.OFFWORK_MINUTE] = schedule.offworkMinute
        }
    }
}