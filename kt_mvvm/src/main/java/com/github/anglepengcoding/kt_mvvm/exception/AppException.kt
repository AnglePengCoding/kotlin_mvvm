package com.github.anglepengcoding.kt_mvvm.exception

import com.github.anglepengcoding.kt_mvvm.base.parseErrorString
import java.lang.Exception

/**
 * @author AnglePenCoding
 * Created by on 2022/9/23 0023
 * @website https://github.com/AnglePengCoding
 */
class AppException : Exception {

    var errorMsg: String

    constructor(error: String?) : super() {
        errorMsg = error ?: parseError(null)
    }

    constructor(throwable: Throwable?) : super() {
        errorMsg = parseError(throwable)
    }

    private fun parseError(throwable: Throwable?): String {
        return throwable.parseErrorString()
    }
}