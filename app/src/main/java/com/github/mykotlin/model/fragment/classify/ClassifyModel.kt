package com.github.mykotlin.model.fragment.classify

import androidx.lifecycle.MutableLiveData
import com.github.anglepengcoding.kt_mvvm.base.BaseViewModel
import com.github.anglepengcoding.kt_mvvm.base.VmLiveData
import com.github.anglepengcoding.kt_mvvm.base.launchVmRequest
import com.github.mykotlin.bean.Sensor

/**
 * @author AnglePenCoding
 * Created by on 2022/9/27 0027
 * @website https://github.com/AnglePengCoding
 */
class ClassifyModel : BaseViewModel() {

    private val classifyRepository by lazy { ClassifyRepository() }
    val refresh: VmLiveData<Sensor> = MutableLiveData()
    val more: VmLiveData<Sensor> = MutableLiveData()

    fun onRefresh(){
        launchVmRequest({
            classifyRepository.onRefresh()
        }, refresh)
    }

    fun loadMore(){
        launchVmRequest({
            classifyRepository.loadMore()
        }, more)
    }

}