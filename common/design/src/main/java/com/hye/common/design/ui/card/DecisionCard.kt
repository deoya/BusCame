package com.hye.common.design.ui.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.hye.common.design.theme.DesignTheme

@Preview
@Composable
fun DecisionCard_Preview() {
    DecisionCard(
        leadingIcon = {},
        trailingBadge = {},
        title = {},
        description = {},
        actions = {}
    )
}

@Composable
fun DecisionCard(
    leadingIcon: @Composable () -> Unit,
    trailingBadge: @Composable () -> Unit,
    title: @Composable () -> Unit,
    description: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.White
) {
    AppCard(
        modifier = modifier,
        shape = RoundedCornerShape(DesignTheme.dimens.radiusXl),
        containerColor = containerColor,
        elevation = DesignTheme.dimens.elevationSm
    ) {
        Column(
            modifier = Modifier.padding(DesignTheme.dimens.spaceLg),
            verticalArrangement = Arrangement.spacedBy(DesignTheme.dimens.spaceLg),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                leadingIcon()
                trailingBadge()
            }

            title()
            description()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(DesignTheme.dimens.spaceMd)
            ) {
                actions()
            }
        }
    }
}