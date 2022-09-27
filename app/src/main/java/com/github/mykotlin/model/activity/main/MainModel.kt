package com.github.mykotlin.model.activity.main

import androidx.lifecycle.MutableLiveData
import com.github.anglepengcoding.kt_mvvm.base.BaseViewModel
import com.github.anglepengcoding.kt_mvvm.base.VmLiveData
import com.github.anglepengcoding.kt_mvvm.base.launchVmRequest
import com.github.mykotlin.Test

/**
 * @author AnglePenCoding
 * Created by on 2022/9/23 0023
 * @website https://github.com/AnglePengCoding
 */
class MainModel : BaseViewModel() {

    private val mainRepository by lazy { MainRepository() }

    val refreshData: VmLiveData<Test> = MutableLiveData()
    fun onRefresh() {
        launchVmRequest({
            mainRepository.onRefresh()
        }, refreshData)
    }
}