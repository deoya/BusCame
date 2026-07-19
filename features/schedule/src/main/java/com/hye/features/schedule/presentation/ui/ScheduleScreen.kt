package com.hye.features.schedule.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.hye.common.design.base.BaseScreenTemplate
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.ui.card.AppCard
import com.hye.domain.model.common.UiStateResult
import com.hye.domain.model.schedule.DayOfWeek
import com.hye.domain.model.schedule.TimePickerType
import com.hye.features.schedule.presentation.ui.component.ScheduleCardTitle
import com.hye.features.schedule.presentation.ui.component.WeekDaySelector
import com.hye.features.schedule.presentation.ui.component.WorkTimeField
import com.hye.features.schedule.presentation.ui.component.WorkTimePicker
import com.hye.features.schedule.presentation.viewmodel.ScheduleEffect
import com.hye.features.schedule.presentation.viewmodel.ScheduleIntent
import com.hye.features.schedule.presentation.viewmodel.ScheduleViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ScheduleScreen(
    viewModel: ScheduleViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    // 다이얼로그 노출 여부
    var currentPickerType by remember { mutableStateOf<TimePickerType?>(null) }

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is ScheduleEffect.ShowCommuteTimePicker -> currentPickerType =
                    TimePickerType.COMMUTE

                is ScheduleEffect.ShowOffworkTimePicker -> currentPickerType =
                    TimePickerType.OFFWORK
            }
        }
    }
    BaseScreenTemplate(
        screenName = "ScheduleScreen",
        viewModel = viewModel,
        isLoading = state.scheduleLoadState is UiStateResult.Loading,
        errorMessage = null,
    ) {
        AppCard(verticalArrangement = Arrangement.spacedBy(DesignTheme.dimens.spaceSm)) {
            // 타이틀 영역
            ScheduleCardTitle()
            // 요일 선택 영역
            WeekDaySelector(
                availableDays = DayOfWeek.entries,
                selectedDays = state.schedule.activeDays,
                onDayToggle = { day ->
                    viewModel.processIntent(ScheduleIntent.ToggleDay(day))
                },
                modifier = Modifier.fillMaxWidth()
            )
            // 시간 설정 영역
            WorkTimeField(
                schedule = state.schedule,
                onCommuteTimeClick = {
                    viewModel.processIntent(ScheduleIntent.ClickCommuteTime)
                },
                onOffworkTimeClick = {
                    viewModel.processIntent(ScheduleIntent.ClickOffworkTime)
                }
            )
            // 3. currentPickerType이 null이 아닐 때만 다이얼로그를 화면에 그림
            currentPickerType?.let { pickerType ->
                val isCommute = pickerType == TimePickerType.COMMUTE
                val initialHour =
                    if (isCommute) state.schedule.commuteHour else state.schedule.offworkHour
                val initialMinute =
                    if (isCommute) state.schedule.commuteMinute else state.schedule.offworkMinute

                WorkTimePicker(
                    initialHour = initialHour,
                    initialMinute = initialMinute,
                    onConfirm = { hour, minute ->
                        currentPickerType = null
                        if (isCommute) {
                            viewModel.processIntent(ScheduleIntent.UpdateCommuteTime(hour, minute))
                        } else {
                            viewModel.processIntent(ScheduleIntent.UpdateOffworkTime(hour, minute))
                        }
                    },
                    onDismiss = {
                        currentPickerType = null
                    }
                )
            }

        }
    }
}

