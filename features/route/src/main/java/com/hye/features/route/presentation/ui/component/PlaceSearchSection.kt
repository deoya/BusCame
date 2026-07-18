package com.hye.features.route.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.hye.common.design.theme.DesignTheme
import com.hye.common.design.ui.text.BodyText
import com.hye.common.design.ui.text.TextStyleSize
import com.hye.common.theme.AppTheme
import com.hye.domain.model.common.UiStateResult
import com.hye.domain.model.route.Place

@Composable
fun PlaceSearchSection(
    searchQuery: String = "",
    onUpdateSearchQuery: (String) -> Unit,
    onPlaceSelected: (Place) -> Unit,
    searchResults: UiStateResult<List<Place>>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(DesignTheme.dimens.spaceMd)
    ) {
        // 검색창
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onUpdateSearchQuery,
            placeholder = { Text("기준이 될 장소나 주소를 검색하세요") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "검색") },
            trailingIcon = {
                if (searchQuery.isNotBlank()) {
                    IconButton(onClick = { onUpdateSearchQuery("") }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "지우기")
                    }
                }
            },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = DesignTheme.colors.surface.copy(alpha = 0.95f),
                unfocusedContainerColor = DesignTheme.colors.surface.copy(alpha = 0.9f),
                focusedBorderColor = DesignTheme.colors.primary,
                unfocusedBorderColor = DesignTheme.colors.onSurfaceVariant
            ),
            shape = RoundedCornerShape(DesignTheme.dimens.radiusLg)
        )

        // 자동완성 리스트
        if (searchResults is UiStateResult.Success && searchQuery.isNotBlank()) {
            val places = (searchResults as UiStateResult.Success).data
            if (places.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DesignTheme.colors.surface)
                        .heightIn(max = AppTheme.dimens.maxSearchResultsHeight)
                ) {
                    items(places) { place ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onPlaceSelected(place) }
                                .padding(DesignTheme.dimens.spaceMd)
                        ) {
                            BodyText(
                                text = place.name,
                                style = TextStyleSize.Medium,
                                fontWeight = FontWeight.Bold
                            )
                            BodyText(
                                text = place.address,
                                style = TextStyleSize.Small,
                                color = DesignTheme.colors.onSurfaceVariant
                            )
                        }
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}