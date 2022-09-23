package com.github.anglepengcoding.kt_mvvm.ext

import com.github.anglepengcoding.kt_mvvm.base.BaseData
import com.github.anglepengcoding.kt_mvvm.base.VmLiveData
import com.github.anglepengcoding.kt_mvvm.base.VmState
import com.github.anglepengcoding.kt_mvvm.exception.AppException

/**
 * @author AnglePenCoding
 * Created by on 2022/9/23 0023
 * @website https://github.com/AnglePengCoding
 */

/**
 * 处理返回值
 *
 * @param result 请求结果
 */
fun <T> VmLiveData<T>.paresVmResult(result: BaseData<T>) {
    value = if (result.dataRight()) VmState.Success(result.result) else
        VmState.Error(AppException(result.msg))
}

/**
 * 异常转换异常处理
 */
fun <T> VmLiveData<T>.paresVmException(e: Throwable) {
    this.value = VmState.Error(AppException(e))
}