package com.github.anglepengcoding.kt_mvvm.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.BusUtils
import com.blankj.utilcode.util.LogUtils
import com.github.anglepengcoding.kt_mvvm.R
import kotlinx.android.synthetic.main.fragment_base.*

/**
 * @author AnglePenCoding
 * Created by on 2022/9/23 0023
 * @website https://github.com/AnglePengCoding
 */
abstract class BaseFragment : Fragment(), View.OnClickListener, IBaseUIView {

    lateinit var mContext: Context

    var baseView: View? = null  //公共布局view
    var contentView: View? = null  //显示的contentView

    abstract fun getLayoutID(): Int //获取布局资源文件

    abstract fun initView() //初始化view

    abstract fun initData() //初始化数据

    abstract fun initPresenter()//网络请求

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LogUtils.e(this::class.java.simpleName + ">>> onCreateView <<<")
        BusUtils.register(this)
        baseView = inflater.inflate(R.layout.fragment_base, container, false) //用布局填充器填充布局
        contentView = inflater.inflate(getLayoutID(), container, false)
        return baseView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        LogUtils.e(this::class.java.simpleName + ">>> onViewCreated <<<")
        base_main.addView(contentView) //将显示布局添加进来
        initView() //初始化控件
        initData()
        initPresenter()
    }


    override fun onAttach(context: Context) {
        LogUtils.e(this::class.java.simpleName + ">>> onAttach <<<")
        super.onAttach(context)
        mContext = context
    }

    override fun onResume() {
        LogUtils.e(this::class.java.simpleName + ">>> onResume <<<")
        super.onResume()
    }

    override fun onDestroyView() {
        LogUtils.e(this::class.java.simpleName + ">>> onDestroyView <<<")
        super.onDestroyView()
    }

    override fun onPause() {
        LogUtils.e(this::class.java.simpleName + ">>> onPause <<<")
        super.onPause()
    }

    override fun onDestroy() {
        LogUtils.e(this::class.java.simpleName + ">>> onDestroy <<<")
        super.onDestroy()
        BusUtils.unregister(this)
    }


    /**
     * 加载中
     */
    fun showLoadingDialog() {
        mLottie!!.setAnimation("loading.json")
        mLottie.playAnimation()
    }

    fun dismissLoadingDialog() {
        mBtAgain.visibility = View.GONE
        mTvAgain.visibility = View.GONE
        mLottie.visibility = View.GONE
    }

    /**
     * 无网络
     */
    fun unknownHostDialog() {
        mTvAgain.visibility = View.VISIBLE
        mBtAgain.visibility = View.VISIBLE
        mTvAgain.text = "无网络"
        mLottie!!.setAnimation("unknownHost.json")
        mLottie.playAnimation()
    }

    /**
     * 网络超时
     */
    fun timeOutDialog() {
        mTvAgain.visibility = View.VISIBLE
        mBtAgain.visibility = View.VISIBLE
        mTvAgain.text = "网络超时"
        mLottie!!.setAnimation("unknownHost.json")
        mLottie.playAnimation()
    }


    /**
     * 数据错误,json错误
     */
    fun jsonSyntaxDialog() {
        mTvAgain.visibility = View.VISIBLE
        mBtAgain.visibility = View.VISIBLE
        mTvAgain.text = "数据错误"
        mLottie!!.setAnimation("json_syntax.json")
        mLottie.playAnimation()
    }

    /**
     * 网络错误
     */
    fun socketDialog() {
        mTvAgain.visibility = View.VISIBLE
        mBtAgain.visibility = View.VISIBLE
        mTvAgain.text = "网络错误"
        mLottie!!.setAnimation("unknownHost.json")
        mLottie.playAnimation()
    }

    /**
     * 未知错误
     */
    fun elseNetDialog() {
        mTvAgain.visibility = View.VISIBLE
        mBtAgain.visibility = View.VISIBLE
        mTvAgain.text = "未知错误"
        mLottie!!.setAnimation("json_syntax.json")
        mLottie.playAnimation()
    }
}