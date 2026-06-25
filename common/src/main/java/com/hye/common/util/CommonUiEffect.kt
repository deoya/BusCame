package com.hye.common.util

sealed interface CommonUiEffect {
    data class ShowToast(val message: String) : CommonUiEffect
    data object NavigateBack : CommonUiEffect // 뒤로가기
}