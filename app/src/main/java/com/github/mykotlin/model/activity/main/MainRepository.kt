package com.github.mykotlin.model.activity.main

import com.github.anglepengcoding.kt_mvvm.base.BaseData
import com.github.mykotlin.Test
import com.github.mykotlin.Constants.Companion.WAN_ANDROID_BASEURL
import com.github.mykotlin.Constants.Companion.WAN_ARTICLE
import rxhttp.RxHttp
import rxhttp.toClass

/**
 * @author AnglePenCoding
 * Created by on 2022/9/23 0023
 * @website https://github.com/AnglePengCoding
 */
class MainRepository {

    suspend fun getTest(url: String): BaseData<Test> {
        val result = RxHttp.get(url)
            .addQuery("type", "led")
            .toClass<Test>()
            .await()
        return BaseData("", result, "200")
    }

    suspend fun onRefresh(): BaseData<Test> = getTest(WAN_ANDROID_BASEURL + WAN_ARTICLE)

}