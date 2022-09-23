package com.github.anglepengcoding.kt_mvvm.ext

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.ToastUtils
import com.github.anglepengcoding.kt_mvvm.base.BaseActivity
import com.github.anglepengcoding.kt_mvvm.base.BaseFragment

/**
 * @author AnglePenCoding
 * Created by on 2022/9/23 0023
 * @website https://github.com/AnglePengCoding
 */


/**
 * 设置onclick事件
 */
fun setViewsOnClickListener(listener: View.OnClickListener, vararg views: View){
    views.forEach { it.setOnClickListener(listener) }
}


/**
 * context显示toast
 */
fun Context.showToast(tips: String){
    ToastUtils.showShort(tips)
}

/**
 * Fragment显示toast
 */
fun Fragment.showToast(tips: String){
    ToastUtils.showShort(tips)
}

/**
 * 隐藏键盘
 */
fun Context.hideSoftInput(){
    val imm: InputMethodManager =
        this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm?.hideSoftInputFromWindow((this as Activity).window.decorView.windowToken, 0)
}

/**
 * 快捷创建viewmodel
 */
inline fun<reified T: ViewModel> BaseActivity.getViewModel(): T = ViewModelProvider(this).get(T::class.java)

/**
 * 快捷创建viewmodel
 */
inline fun<reified T: ViewModel> BaseFragment.getViewModel(): T = ViewModelProvider(this).get(T::class.java)