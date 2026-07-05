package com.hye.features.schedule.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.hye.common.design.base.BaseScreenTemplate
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.ui.card.AppCard
import com.hye.domain.model.DayOfWeek
import com.hye.features.schedule.presentation.ui.component.ScheduleCardTitle
import com.hye.features.schedule.presentation.ui.component.WeekDaySelector
import com.hye.features.schedule.presentation.ui.component.WorkTimeField
import com.hye.features.schedule.presentation.viewmodel.ScheduleEffect
import com.hye.features.schedule.presentation.viewmodel.ScheduleIntent
import com.hye.features.schedule.presentation.viewmodel.ScheduleViewModel
import kotlinx.coroutines.flow.collectLatest

@Preview(showBackground = true, backgroundColor = 0x330000ff)
@Composable
fun ScheduleScreen_Preview() {
    ScheduleScreen()
}

@Composable
fun ScheduleScreen(
    viewModel: ScheduleViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is ScheduleEffect.ShowCommuteTimePicker -> {
                    // TODO: 출근 시간 선택 타임피커 다이얼로그 띄우기
                }

                is ScheduleEffect.ShowOffworkTimePicker -> {
                    // TODO: 퇴근 시간 선택 타임피커 다이얼로그 띄우기
                }
            }
        }
    }
    BaseScreenTemplate(
        screenName = "ScheduleScreen",
        viewModel = viewModel,
        isLoading = state.isLoading,
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
        }
    }
}

