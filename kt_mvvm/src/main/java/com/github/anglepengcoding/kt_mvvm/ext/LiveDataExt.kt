package com.github.anglepengcoding.kt_mvvm.base

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * @author AnglePenCoding
 * Created by on 2022/9/23 0023
 * @website https://github.com/AnglePengCoding
 */


@MainThread
inline fun <T> LiveData<T>.observe(
    owner: LifecycleOwner,
    crossinline onChanged: (T) -> Unit
): Observer<T> {
    val observer = Observer<T> {
        onChanged(it)
    }
    observe(owner, observer)
    return observer
}

typealias VmLiveData<T> = MutableLiveData<VmState<T>>