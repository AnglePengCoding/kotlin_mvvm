package com.github.anglepengcoding.kt_mvvm.ext

import com.google.gson.Gson

/**
 * @author AnglePenCoding
 * Created by on 2022/9/23 0023
 * @website https://github.com/AnglePengCoding
 */

/**
 * 转换String
 */
fun Any?.toJsonString(): String {
    return Gson().toJson(this)
}

/**
 * 转换成对象
 */
inline fun <reified T> String.toJsonObject(): T {
    return Gson().fromJson(this, T::class.java)
}