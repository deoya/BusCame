package com.hye.features.schedule.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BusinessCenter
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.ui.badge.TagBadge
import com.hye.common.design.ui.text.LabelText
import com.hye.common.design.ui.text.TextStyleSize

@Composable
fun ScheduleCardTitle() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(DesignTheme.dimens.spaceXxs)
    ) {
        Icon(
            imageVector = Icons.Outlined.BusinessCenter,
            contentDescription = null,
            modifier = Modifier.size(DesignTheme.dimens.iconSmall),
            tint = DesignTheme.colors.onBackground
        )
        LabelText(
            text = "근무 일정",
            style = TextStyleSize.Medium,
            color = DesignTheme.colors.onBackground,
            modifier = Modifier.padding(bottom = 0.dp)
        )
        TagBadge(
            text = "신규",
            bgColor = DesignTheme.colors.success.copy(alpha = 0.1f),
            textColor = DesignTheme.colors.success
        )
    }
}