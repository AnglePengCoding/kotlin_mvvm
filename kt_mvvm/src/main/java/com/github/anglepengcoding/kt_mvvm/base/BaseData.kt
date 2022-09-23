package com.github.anglepengcoding.kt_mvvm.base

import androidx.annotation.Keep


/**
 * @author AnglePenCoding
 * Created by on 2022/9/23 0023
 * @website https://github.com/AnglePengCoding
 */
@Keep
open class BaseData<T>(var msg: String="查询成功",  var result: T,var code: String = "200") {
    /**
     * 数据是否正确，默认实现
     */
    open fun dataRight(): Boolean {
        return code == "200"
    }

}