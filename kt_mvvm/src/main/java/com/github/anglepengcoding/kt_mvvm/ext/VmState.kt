package com.github.anglepengcoding.kt_mvvm.base

import com.github.anglepengcoding.kt_mvvm.exception.AppException

/**
 * @author AnglePenCoding
 * Created by on 2022/9/23 0023
 * @website https://github.com/AnglePengCoding
 */
class VmResult<T> {
    var onAppSuccess: (data: T) -> Unit = {}
    var onAppError: (exception: AppException) -> Unit = {}
    var onAppLoading: () -> Unit = {}
    var onAppComplete: () -> Unit = {}
}


sealed class VmState<out T> {
    object Loading : VmState<Nothing>()
    data class Success<out T>(val data: T) : VmState<T>()
    data class Error(val error: AppException) : VmState<Nothing>()
}