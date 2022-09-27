package com.github.mykotlin.model.fragment.classify

import com.github.anglepengcoding.kt_mvvm.base.BaseData
import com.github.mykotlin.Constants
import com.github.mykotlin.Test
import com.github.mykotlin.bean.Sensor
import rxhttp.RxHttp
import rxhttp.toClass

/**
 * @author AnglePenCoding
 * Created by on 2022/9/27 0027
 * @website https://github.com/AnglePengCoding
 */
class ClassifyRepository {


    private suspend fun getSensorDataStatList(url: String, pagerSize: Int): BaseData<Sensor> {
        val result = RxHttp.get(url)
            .addHeader("userToken","app:6688861f4ffe11ec800384144dd3207e:fda921778596457ca5254a7374eb1483")
            .addQuery("pager.pageNumber", "10")
            .addQuery("pager.pageSize", pagerSize)
            .toClass<Sensor>()
            .await()
        return BaseData("", result, "200")

    }

    private var page = 1

    suspend fun onRefresh(): BaseData<Sensor> =
        getSensorDataStatList(Constants.WAN_ANDROID_BASEURL2 + Constants.WAN_ARTICLE2, page)

    suspend fun loadMore(): BaseData<Sensor> =
        getSensorDataStatList(Constants.WAN_ANDROID_BASEURL2 + Constants.WAN_ARTICLE2, page++)
}