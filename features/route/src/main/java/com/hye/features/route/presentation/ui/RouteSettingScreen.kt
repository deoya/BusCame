package com.hye.features.route.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hye.common.design.base.BaseScreenTemplate
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.ui.card.AppCard
import com.hye.common.design.ui.text.TextStyleSize
import com.hye.common.design.ui.text.TitleText
import com.hye.domain.model.common.UiStateResult
import com.hye.domain.model.route.BusStop
import com.hye.domain.model.route.SelectionMode
import com.hye.features.route.presentation.ui.component.FullScreenOverlay
import com.hye.features.route.presentation.ui.component.MapSection
import com.hye.features.route.presentation.ui.component.PlaceSearchSection
import com.hye.features.route.presentation.ui.component.StationInputSection
import com.hye.features.route.presentation.viewmodel.RouteIntent
import com.hye.features.route.presentation.viewmodel.RouteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RouteSettingScreen(
    viewModel: RouteViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var displayStops by remember { mutableStateOf<List<BusStop>>(emptyList()) }
    var showSelectionDialog by remember { mutableStateOf(false) }

    LaunchedEffect(state.nearbyStopsState) {
        if (state.nearbyStopsState is UiStateResult.Success) {
            displayStops = (state.nearbyStopsState as UiStateResult.Success).data
        }
    }

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

        FullScreenOverlay(
            visible = state.selectionMode != SelectionMode.NONE,
            title = "정류장 위치 선택",
            onDismissRequest = { viewModel.processIntent(RouteIntent.CancelSelection) }
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(0.9f)
            ) {

                Box(modifier = Modifier.weight(1f)) {
                    MapSection(
                        selectionMode = state.selectionMode,
                        currentMapCenter = state.currentMapCenter,
                        selectedBusStop = state.selectedBusStop,
                        nearbyStops = displayStops,
                        onMapCenterChanged = { lat, lng ->
                            viewModel.processIntent(RouteIntent.MapCenterChanged(lat, lng))
                        },
                        onConfirmSelection = {
                            showSelectionDialog = true
                        },
                        onBusStopPinClick = { stop ->
                            viewModel.processIntent(RouteIntent.ClickBusStopPin(stop))
                        },
                    )
                }
                PlaceSearchSection(
                    searchQuery = state.searchQuery,
                    onUpdateSearchQuery = { query ->
                        viewModel.processIntent(RouteIntent.UpdateSearchQuery(query))
                    },
                    onPlaceSelected = { place ->
                        viewModel.processIntent(RouteIntent.SelectPlace(place))
                    },
                    searchResults = state.searchResults
                )
            }
        }
        if (showSelectionDialog && state.selectedBusStop != null) {
            androidx.compose.ui.window.Dialog(
                onDismissRequest = { showSelectionDialog = false }
            ) {
                AppCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = DesignTheme.dimens.spaceLg)
                ) {
                    Column(
                        modifier = Modifier.padding(DesignTheme.dimens.spaceLg),
                        verticalArrangement = Arrangement.spacedBy(DesignTheme.dimens.spaceMd),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TitleText(
                            text = state.selectedBusStop?.name ?: "",
                            style = TextStyleSize.Medium
                        )

                        val modeTargetText =
                            if (state.selectionMode == SelectionMode.DEPARTURE) "출발지" else "도착지"
                        Text(
                            text = "이 정류장을 $modeTargetText 로 최종 설정할까요?",
                            color = DesignTheme.colors.onSurfaceVariant
                        )

                        Button(
                            onClick = {
                                showSelectionDialog = false // 창 닫기
                                viewModel.processIntent(RouteIntent.ConfirmSelection)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = DesignTheme.colors.primary)
                        ) {
                            Text("확인", color = DesignTheme.colors.onPrimary)
                        }

                        androidx.compose.material3.TextButton(
                            onClick = { showSelectionDialog = false }
                        ) {
                            Text("취소", color = DesignTheme.colors.border)
                        }
                    }
                }
            }
        }


    }
}
