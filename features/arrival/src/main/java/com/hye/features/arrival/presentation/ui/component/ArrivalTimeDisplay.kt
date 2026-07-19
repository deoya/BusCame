package com.hye.features.arrival.presentation.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.ui.text.BodyText
import com.hye.common.design.ui.text.DisplayText
import com.hye.common.design.ui.text.TextStyleSize


@Composable
fun ArrivalTimeDisplay(arrivalMinutes: Int, arrivalTime: String) {
    Row(verticalAlignment = Alignment.Bottom) {
        DisplayText(
            text = "$arrivalMinutes",
            size = 40.sp,
            weight = FontWeight.Bold,
            color = DesignTheme.colors.success,
            modifier = Modifier.alignByBaseline()
        )
        BodyText(
            text = "분 후 도착",
            style = TextStyleSize.Medium,
            color = DesignTheme.colors.onSurfaceVariant,
            modifier = Modifier.padding(
                start = DesignTheme.dimens.spaceXxs,
                bottom = DesignTheme.dimens.spaceXs
            )
        )
        BodyText(
            text = "($arrivalTime)",
            style = TextStyleSize.Small,
            color = DesignTheme.colors.onSurfaceVariant,
            modifier = Modifier.padding(
                start = DesignTheme.dimens.spaceXxs,
                bottom = DesignTheme.dimens.spaceMicro
            )
        )
    }
}