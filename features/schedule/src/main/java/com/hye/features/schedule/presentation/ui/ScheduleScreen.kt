package com.hye.features.schedule.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hye.common.design.base.BaseScreenTemplate
import com.hye.common.design.base.BaseViewModel
import com.hye.common.design.mock.DayOfWeek
import com.hye.common.design.mock.WorkSchedule
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.ui.card.AppCard
import com.hye.features.schedule.presentation.ui.component.ScheduleCardTitle
import com.hye.features.schedule.presentation.ui.component.WeekDaySelector
import com.hye.features.schedule.presentation.ui.component.WorkTimeField

@Preview(showBackground = true, backgroundColor = 0x330000ff)
@Composable
fun ScheduleScreen_Preview() {
    ScheduleScreen(
        WorkSchedule(),
        {},
        {},
        {}
    )
}

@Composable
fun ScheduleScreen(
    schedule: WorkSchedule,
    onDayToggle: (DayOfWeek) -> Unit,
    onCommuteTimeClick: () -> Unit,
    onOffworkTimeClick: () -> Unit,
) {
    BaseScreenTemplate(
        screenName = "ScheduleScreen",
        viewModel = BaseViewModel(),
        isLoading = false,
        errorMessage = null,
        setTopBarActions = {},
        topBarActionContent = {},
        onNavigateBack = {},
        loadingContent = {},
        errorContent = {},
    ) {
        AppCard(verticalArrangement = Arrangement.spacedBy(DesignTheme.dimens.spaceSm)) {
            // 타이틀 영역
            ScheduleCardTitle()
            // 요일 선택 영역
            WeekDaySelector(
                availableDays = DayOfWeek.entries,
                selectedDays = schedule.activeDays,
                onDayToggle = onDayToggle,
                modifier = Modifier.fillMaxWidth()
            )
            // 시간 설정 영역
            WorkTimeField(
                schedule,
                onCommuteTimeClick,
                onOffworkTimeClick
            )
        }
    }
}