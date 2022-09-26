package com.github.anglepengcoding.kt_mvvm.base

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.BarUtils.*
import com.blankj.utilcode.util.BusUtils
import com.blankj.utilcode.util.LogUtils
import com.github.anglepengcoding.kt_mvvm.R
import com.github.anglepengcoding.kt_mvvm.ext.hideSoftInput
import com.jakewharton.rxbinding4.view.clicks
import kotlinx.android.synthetic.main.activity_base.*
import java.util.concurrent.TimeUnit

/**
 * @author AnglePenCoding
 * Created by on 2022/9/22 0022
 * @website https://github.com/AnglePengCoding
 */
open abstract class BaseActivity : AppCompatActivity(), IBaseUIView {

    /*  开发者自由发挥 */
    var leftImageView: ImageView? = null //标题栏左键
    var rightImageView: ImageView? = null //标题栏右键
    var mRightText: TextView? = null //标题栏右文字
    var mTitleText: TextView? = null //标题栏文字

    abstract fun displayStatusBar(): Boolean//是否显示标题栏

    abstract fun createLayout(): Int //添加布局

    abstract fun initView() //初始化view

    abstract fun initData() //初始化数据

    abstract fun initPresenter()//网络请求

    open fun getBundleExtras(extras: Bundle?) {} //接收bundle数据

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.e(this::class.java.simpleName + ">>> onCreate <<<")
        initStatusBar()
        initViews()
        setChildView()
        setListener()
        BusUtils.register(this)
        intent.extras?.let { getBundleExtras(it) }
        init()
    }

    open fun setListener() {
        leftImageView?.clicks()
            ?.throttleFirst(2000, TimeUnit.MICROSECONDS)
            ?.subscribe {
                hideSoftInput()
                finish()
            }

        mBtAgain?.clicks()?.throttleFirst(2000, TimeUnit.MICROSECONDS)
            ?.subscribe {
                initPresenter()
            }
    }

    private fun initViews() {
        mTitleText = findViewById(R.id.mTitleText)
        mRightText = findViewById(R.id.mRightText)
        rightImageView = findViewById(R.id.rightImageView)
        leftImageView = findViewById(R.id.leftImageView)
    }

    private fun setChildView() {
        val view = LayoutInflater.from(this).inflate(createLayout(), mFrameLayout, false)
        mFrameLayout.addView(view)
    }

    private fun initStatusBar() {
        transparentStatusBar(this)
        setStatusBarLightMode(this, true)
        setContentView(R.layout.activity_base)
        setNavBarColor(this, Color.WHITE)
        displayToolbarLayout()
        addMarginTopEqualStatusBarHeight(mRlTitleLayout)
    }


    private fun init() {
        initView()
        initData()
        initPresenter()
    }

    private fun displayToolbarLayout() {
        if (displayStatusBar()) {
            mRlTitleLayout.visibility = View.VISIBLE
        } else {
            mRlTitleLayout.visibility = View.GONE
        }
    }

    override fun onStart() {
        LogUtils.e(this::class.java.simpleName + ">>> onStart <<<")
        super.onStart()
    }

    override fun onStop() {
        LogUtils.e(this::class.java.simpleName + ">>> onStop <<<")
        super.onStop()
    }

    override fun onResume() {
        LogUtils.e(this::class.java.simpleName + ">>> onResume <<<")
        super.onResume()
    }

    override fun onPause() {
        mLottie?.pauseAnimation()
        LogUtils.e(this::class.java.simpleName + ">>> onPa use <<<")
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.e(this::class.java.simpleName + ">>> onDestroy <<<")
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