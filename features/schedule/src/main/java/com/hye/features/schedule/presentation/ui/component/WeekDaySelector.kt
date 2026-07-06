package com.hye.features.schedule.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.ui.text.LabelText
import com.hye.common.design.ui.text.TextStyleSize
import com.hye.common.extension.label
import com.hye.domain.model.schedule.DayOfWeek

@Composable
fun WeekDaySelector(
    availableDays: List<DayOfWeek>,
    selectedDays: Set<DayOfWeek>,
    onDayToggle: (DayOfWeek) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(DesignTheme.dimens.spaceXxs)
    ) {
        availableDays.forEach { day ->
            val isActive = day in selectedDays
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .background(
                        color = if (isActive) DesignTheme.colors.primary else DesignTheme.colors.disabledContainer,
                        shape = RoundedCornerShape(DesignTheme.dimens.radiusSm)
                    )
                    .clickable { onDayToggle(day) },
                contentAlignment = Alignment.Center
            ) {
                LabelText(
                    text = day.label,
                    style = TextStyleSize.Medium,
                    color = if (isActive) DesignTheme.colors.onPrimary else DesignTheme.colors.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 0.dp)
                )
            }
        }
    }
}