package com.github.mykotlin

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

/**
 * @author AnglePenCoding
 * Created by on 2022/9/23 0023
 * @website https://github.com/AnglePengCoding
 */

@Keep
@Parcelize
data class Test(
    val createdTime: String,
    val updatedTime: String,
    val id: String,
    val versionCode: String,
    val url: String,
    val remark: String
) : Parcelable