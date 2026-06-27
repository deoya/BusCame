package com.hye.features.schedule.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NightsStay
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hye.common.design.mock.WorkSchedule
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.ui.filed.GenericClickableField

@Composable
fun WorkTimeField(
    schedule: WorkSchedule,
    onCommuteTimeClick: () -> Unit,
    onOffworkTimeClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(DesignTheme.dimens.spaceXs)
    ) {
        GenericClickableField(
            label = "출근",
            value = String.format("%02d:%02d", schedule.commuteHour, schedule.commuteMinute),
            icon = Icons.Outlined.WbSunny,
            onClick = onCommuteTimeClick,
            modifier = Modifier.weight(1f)
        )
        GenericClickableField(
            label = "퇴근",
            value = String.format("%02d:%02d", schedule.offworkHour, schedule.offworkMinute),
            icon = Icons.Outlined.NightsStay,
            onClick = onOffworkTimeClick,
            modifier = Modifier.weight(1f)
        )
    }
}