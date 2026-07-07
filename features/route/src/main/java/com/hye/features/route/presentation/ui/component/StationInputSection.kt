package com.hye.features.route.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.ui.text.LabeledSelectorField
import com.hye.domain.model.map.CommuteRoute

@Composable
fun StationInputSection(
    route: CommuteRoute,
    onDepartureStopClick: () -> Unit,
    onArrivalStopClick: () -> Unit
) {
    val noneBus = ""

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(DesignTheme.dimens.spaceXs)
    ) {
        LabeledSelectorField(
            label = "출발 정거장",
            value = route.departureStop?.name ?: noneBus,
            modifier = Modifier.weight(1f),
            onClick = onDepartureStopClick
        )
        Icon(
            imageVector = Icons.Outlined.ArrowForward,
            contentDescription = "에서",
            tint = DesignTheme.colors.onSurfaceVariant,
            modifier = Modifier.size(DesignTheme.dimens.iconMediumSmall)
        )
        LabeledSelectorField(
            label = "도착 정거장",
            value = route.destinationStop?.name ?: noneBus,
            modifier = Modifier.weight(1f),
            onClick = onArrivalStopClick
        )
    }
}