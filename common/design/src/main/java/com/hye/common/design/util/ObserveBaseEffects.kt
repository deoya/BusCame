package com.hye.common.design.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.hye.common.design.base.BaseViewModel

@Composable
fun ObserveBaseEffects(
    viewModel: BaseViewModel,
    onNavigateBack: () -> Unit = {}
) {
    val context = LocalContext.current

    LaunchedEffect(viewModel.commonEffect) {
        viewModel.commonEffect.collect { effect ->
            UiEffectHelper.handle(
                context = context,
                effect = effect,
                onNavigateBack = onNavigateBack
            )
        }
    }
}