package com.hye.features.arrival.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.ui.badge.TagBadge
import com.hye.common.design.ui.text.BodyText
import com.hye.common.design.ui.text.TextStyleSize
import com.hye.common.design.ui.text.TitleText
import com.hye.common.mock.BusArrivalInfo


@Composable
fun BusHeader(busInfo: BusArrivalInfo) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(DesignTheme.dimens.spaceXs)
        ) {
            TagBadge("버스", DesignTheme.colors.primary, DesignTheme.colors.onPrimary)
            TitleText(
                text = busInfo.routeNumber,
                style = TextStyleSize.Medium,
                fontWeight = FontWeight.SemiBold,
                color = DesignTheme.colors.onBackground
            )
            BodyText(
                text = busInfo.direction,
                style = TextStyleSize.Small,
                color = DesignTheme.colors.onSurfaceVariant
            )
        }
    }
}