package com.github.mykotlin.model.main

import androidx.activity.viewModels
import com.github.anglepengcoding.kt_mvvm.base.BaseActivity
import com.github.anglepengcoding.kt_mvvm.base.vmObserverLoading
import com.github.mykotlin.R
import androidx.lifecycle.rxLifeScope
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    val mainModel by viewModels<MainModel>()

    override fun getChildTitle(): String? {
        return "ceshi"
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun initMainNetData() {
        mainModel.refreshData.vmObserverLoading(this){
            mTvTxt.text = it.remark
        }

        rxLifeScope.launch {
            mainModel.onRefresh()
        }
    }

    override fun showLoadingDialog(content: String) {
    }

    override fun dismissLoadingDialog() {
    }


}