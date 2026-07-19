package com.hye.features.arrival.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.hye.common.design.base.BaseScreenTemplate
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.ui.button.ToggleButton
import com.hye.common.design.ui.card.AppCard
import com.hye.common.extension.label
import com.hye.common.mock.sampleBusInfo
import com.hye.domain.model.arrival.CommuteDirection
import com.hye.features.arrival.presentation.ui.component.ArrivalTimeDisplay
import com.hye.features.arrival.presentation.ui.component.BoardToggleButton
import com.hye.features.arrival.presentation.ui.component.BusHeader
import com.hye.features.arrival.presentation.ui.component.NextBusInfo
import com.hye.features.arrival.presentation.viewmodel.BusArrivalViewModel

@Preview
@Composable
fun BusArrivalScreen(
    viewModel: BusArrivalViewModel = hiltViewModel()
) {
    val busInfo = sampleBusInfo()
    var direction by rememberSaveable { mutableStateOf(CommuteDirection.TO_WORK) }
    var isBoarded by rememberSaveable { mutableStateOf(false) }
    BaseScreenTemplate(
        viewModel = viewModel,
        isLoading = false,
        errorMessage = null
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(DesignTheme.dimens.spaceSm),
        ) {
            // 출근/퇴근 토글
            ToggleButton(
                options = CommuteDirection.entries,
                selected = direction,
                onSelect = {
                    direction = it
                    isBoarded = false // 방향이 바뀌면 탑승 상태 초기화
                },
                label = { it.label }
            )

            AppCard(verticalArrangement = Arrangement.spacedBy(DesignTheme.dimens.spaceSm)) {
                //버스 정보
                BusHeader(busInfo)
                if (true) {
                    ArrivalTimeDisplay(
                        arrivalMinutes = busInfo.arrivalMinutes,
                        arrivalTime = busInfo.arrivalTime
                    )
                }
                BoardToggleButton(false, {})
            }
            //다음 버스
            NextBusInfo(9, "00:00")
        }
    }
}

