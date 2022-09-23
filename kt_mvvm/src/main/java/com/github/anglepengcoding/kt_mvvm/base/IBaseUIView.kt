package com.github.anglepengcoding.kt_mvvm.base

/**
 * @author AnglePenCoding
 * Created by on 2022/9/23 0023
 * @website https://github.com/AnglePengCoding
 */
interface IBaseUIView {

    /** 获取当前布局状态,方便在callBack中统一处理  */
    fun getBaseViewStatus(): EBaseViewStatus?

    /** 手动设置布局状态,一般情况不需要  */
    fun setBaseViewStatus(baseViewStatus: EBaseViewStatus?)

    /** 显示成功布局  */
    fun showSuccessLayout()

    /** 显示加载中布局  */
    fun showLoadingLayout()

    /** 显示失败布局  */
    fun showErrorLayout(errorMsg: String?)

}