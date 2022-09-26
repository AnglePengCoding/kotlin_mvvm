package com.github.mykotlin.model.main

import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.rxLifeScope
import com.github.anglepengcoding.kt_mvvm.base.BaseActivity
import com.github.anglepengcoding.kt_mvvm.base.vmObserverLoading
import com.github.mykotlin.R

class MainActivity : BaseActivity() {

    val mainModel by viewModels<MainModel>()


    override fun displayStatusBar(): Boolean {
        return false
    }

    override fun createLayout(): Int {
        return R.layout.activity_main
    }


    override fun initView() {
        mTitleText?.text = "测试"
    }

    override fun initData() {
        mainModel.refreshData.vmObserverLoading(this) {
        }
    }

    override fun initPresenter() {
        rxLifeScope.launch {
            mainModel.onRefresh()
        }
    }


}