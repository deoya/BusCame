package com.hye.features.route.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.ui.card.AppCard
import com.hye.common.design.ui.text.TextStyleSize
import com.hye.common.design.ui.text.TitleText
import com.hye.domain.model.map.CommuteRoute
import com.hye.features.route.presentation.ui.component.MapSection
import com.hye.features.route.presentation.ui.component.StationInputSection

@Preview
@Composable
fun RouteSettingScreen() {
    val mock = CommuteRoute(
        departureStop = null,
        destinationStop = null
    )
    RouteSettingCard(
        route = mock,
        onDepartureStopClick = {},
        onArrivalStopClick = {},
        onMapClick = {}
    )
}

@Composable
fun RouteSettingCard(
    route: CommuteRoute,
    onDepartureStopClick: () -> Unit,
    onArrivalStopClick: () -> Unit,
    onMapClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AppCard(verticalArrangement = Arrangement.spacedBy(DesignTheme.dimens.spaceS)) {
        // 타이틀 영역
        TitleText(
            text = "경로 설정",
            style = TextStyleSize.Medium,
            fontWeight = FontWeight.Medium,
            color = DesignTheme.colors.onBackground
        )
        // 정거장 선택 영역
        StationInputSection(
            route = route,
            onDepartureStopClick = onDepartureStopClick,
            onArrivalStopClick = onArrivalStopClick
        )
        MapSection()
    }
}
