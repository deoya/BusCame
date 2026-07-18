package com.hye.domain.model.common

sealed class UiStateResult<out T> {
    data object Idle : UiStateResult<Nothing>()
    data object Loading : UiStateResult<Nothing>()
    data class Success<T>(val data: T) : UiStateResult<T>()
    data class Error(val exception: Throwable) : UiStateResult<Nothing>()
}