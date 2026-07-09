package com.hye.features.route.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hye.common.design.base.BaseScreenTemplate
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.ui.card.AppCard
import com.hye.common.design.ui.text.TextStyleSize
import com.hye.common.design.ui.text.TitleText
import com.hye.domain.model.route.SelectionMode
import com.hye.features.route.presentation.ui.component.MapSection
import com.hye.features.route.presentation.ui.component.StationInputSection
import com.hye.features.route.presentation.viewmodel.RouteIntent
import com.hye.features.route.presentation.viewmodel.RouteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RouteSettingScreen(
    viewModel: RouteViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    BaseScreenTemplate(
        viewModel = viewModel,
        isLoading = false,
        errorMessage = null,
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
                route = state,
                onDepartureStopClick = {
                    viewModel.processIntent(
                        RouteIntent.ClickDepartureInput,
                    )
                },
                onArrivalStopClick = {
                    viewModel.processIntent(
                        RouteIntent.ClickArrivalInput,
                    )
                },
            )
        }

        if (state.selectionMode != SelectionMode.NONE) {
            ModalBottomSheet(
                onDismissRequest = {
                    viewModel.processIntent(RouteIntent.CloseMapBottomSheet)
                },
                sheetState = sheetState,
                containerColor = DesignTheme.colors.surface
            ) {
                Box(modifier = Modifier.fillMaxHeight(0.9f)) {
                    MapSection(
                        selectionMode = state.selectionMode,
                        onMapCenterChanged = { lat, lng ->
                            viewModel.processIntent(RouteIntent.MapCenterChanged(lat, lng))
                        },
                        onConfirmSelection = {
                            viewModel.processIntent(RouteIntent.ConfirmSelection)
                        }
                    )
                }
            }
        }

    }
}
