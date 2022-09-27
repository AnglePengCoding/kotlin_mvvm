package com.github.mykotlin.bean

import android.os.Parcelable
import androidx.annotation.Keep
import com.github.anglepengcoding.kt_mvvm.ext.toJsonString
import kotlinx.android.parcel.Parcelize

/**
 * @author AnglePenCoding
 * Created by on 2022/9/27 0027
 * @website https://github.com/AnglePengCoding
 */

@Keep
@Parcelize
data class Sensor(
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
    val list: MutableList<ListItem>?,
) : Parcelable {
    override fun toString(): String {
        return this.toJsonString()
    }
}

@Parcelize
@Keep
data class ListItem(
    val id: String?,
    val eUnicode: String?,
    val eBusicode: String?,
    val eName: String?
) : Parcelable