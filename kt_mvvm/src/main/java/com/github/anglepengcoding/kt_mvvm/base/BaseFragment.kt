package com.github.anglepengcoding.kt_mvvm.base

import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.BusUtils
import com.github.anglepengcoding.kt_mvvm.R

/**
 * @author AnglePenCoding
 * Created by on 2022/9/23 0023
 * @website https://github.com/AnglePengCoding
 */
abstract class BaseFragment :Fragment() , View.OnClickListener, IBaseUIView{

    lateinit var mContext: Context

    var baseView: View? = null  //公共布局view
    var contentView: View? = null  //显示的contentView

    abstract fun getLayoutID(): Int //获取布局资源文件

    abstract fun initViewModel() //初始化ViewModel

    abstract fun initView() //初始化view

    abstract fun initData() //初始化数据

    abstract fun initMainNetData() //主协议请求数据,适用于必须请求协议才能展示的页面,在initData之前请求

    abstract fun initLazyData() //懒加载数据，如：网络请求获取数据

    open fun isShowLoading(): Boolean { return false } //是否显示加载中布局,如果return true则必须重写initMainNetData方法

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        BusUtils.register(this)
//        baseView = inflater.inflate(R.layout.fragment_base, container, false) //用布局填充器填充布局
        contentView = inflater.inflate(getLayoutID(), container, false)
        return baseView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        base_main.addView(contentView) //将显示布局添加进来
        initViewModel()
        initView() //初始化控件
        initData()
        initMainNetData()
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        BusUtils.unregister(this)
    }

    abstract fun showLoadingDialog(content:  String = "加载中")

    abstract fun dismissLoadingDialog()

}