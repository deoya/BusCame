package com.hye.domain.model.common

sealed class ResultWrapper<out T> {
    data class Success<T>(val data: T) : ResultWrapper<T>()
    data class Error(val exception: Throwable) : ResultWrapper<Nothing>()
}