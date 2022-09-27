package com.github.mykotlin

import android.os.Parcelable
import androidx.annotation.Keep
import com.github.anglepengcoding.kt_mvvm.ext.toJsonString
import kotlinx.android.parcel.Parcelize

/**
 * @author AnglePenCoding
 * Created by on 2022/9/23 0023
 * @website https://github.com/AnglePengCoding
 */

@Keep
@Parcelize
data class Test(
    val msg: String,
    val result: Result,
    val code: String
) : Parcelable {
    override fun toString(): String {
        return this.toJsonString()
    }
}

@Keep
@Parcelize
data class Result(
    val createdTime: String,
    val updatedTime: String,
    val id: String,
    val status: String,
    val versionCode: String,
    val versionName: String,
    val url: String,
    val remark: String,
    val type: String,
) : Parcelable {
    override fun toString(): String {
        return this.toJsonString()
    }
}