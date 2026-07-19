package com.hye.features.arrival.presentation.ui.component


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBusFilled
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.ui.card.AppCard
import com.hye.common.design.ui.text.BodyText
import com.hye.common.design.ui.text.DisplayText
import com.hye.common.design.ui.text.LabelText
import com.hye.common.design.ui.text.TextStyleSize
import com.hye.common.theme.AppTheme

@Composable
fun NextBusInfo(
    remainMinutes: Int,
    arrivalTime: String,
    modifier: Modifier = Modifier,
) {
    AppCard(
        modifier = Modifier.height(AppTheme.dimens.nextBusCardHeight),

        ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = DesignTheme.dimens.spaceLg),
            horizontalArrangement = Arrangement.spacedBy(
                DesignTheme.dimens.spaceMd,
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Filled.DirectionsBusFilled,
                contentDescription = null,
                modifier = Modifier.size(DesignTheme.dimens.iconExtraLarge),
                tint = AppTheme.color.nextBusColor
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(DesignTheme.dimens.spaceXxxs)
            ) {
                LabelText(
                    text = "다음 버스",
                    style = TextStyleSize.Medium,
                    color = DesignTheme.colors.onSurfaceVariant,
                    modifier = Modifier
                )
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(DesignTheme.dimens.spaceXs)
                ) {
                    DisplayText(
                        text = "${remainMinutes}분 후 도착",
                        size = DesignTheme.dimens.textSizeXxl,
                        color = DesignTheme.colors.onBackground,
                        modifier = Modifier.alignByBaseline()
                    )
                    BodyText(
                        text = "($arrivalTime)",
                        style = TextStyleSize.Large,
                        color = DesignTheme.colors.onSurfaceVariant,
                        modifier = Modifier
                            .alignByBaseline()
                            .padding(bottom = DesignTheme.dimens.spaceXxxs)
                    )
                }
            }
        }
    }
}