package com.hye.features.schedule.presentation.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hye.common.design.theme.DesignTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkTimePicker(
    initialHour: Int,
    initialMinute: Int,
    onConfirm: (hour: Int, minute: Int) -> Unit,
    onDismiss: () -> Unit
) {
    val timePickerState = rememberTimePickerState(
        initialHour = initialHour,
        initialMinute = initialMinute,
        is24Hour = false
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onConfirm(timePickerState.hour, timePickerState.minute)
            }) {
                Text("확인")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("취소")
            }
        },
        containerColor = DesignTheme.colors.surface,

        text = {
            TimePicker(
                state = timePickerState,
                colors = TimePickerDefaults.colors(
                    clockDialColor = DesignTheme.colors.surfaceVariant,
                    clockDialSelectedContentColor = DesignTheme.colors.onPrimary,
                    clockDialUnselectedContentColor = DesignTheme.colors.onBackground,
                    selectorColor = DesignTheme.colors.primary,
                    periodSelectorSelectedContainerColor = DesignTheme.colors.primaryContainer,
                    periodSelectorSelectedContentColor = DesignTheme.colors.primary,
                    periodSelectorUnselectedContainerColor = DesignTheme.colors.surface,
                    periodSelectorUnselectedContentColor = DesignTheme.colors.onSurfaceVariant,
                    periodSelectorBorderColor = DesignTheme.colors.border,
                    timeSelectorSelectedContainerColor = DesignTheme.colors.primaryContainer,
                    timeSelectorSelectedContentColor = DesignTheme.colors.primary,
                    timeSelectorUnselectedContainerColor = DesignTheme.colors.surfaceVariant,
                    timeSelectorUnselectedContentColor = DesignTheme.colors.onSurfaceVariant
                ),
                modifier = Modifier.padding(top = DesignTheme.dimens.spaceMd)
            )
        }

    )
}