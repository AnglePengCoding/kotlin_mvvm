package com.github.mykotlin.model.activity.main

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.rxLifeScope
import com.azhon.appupdate.manager.DownloadManager
import com.github.anglepengcoding.kt_mvvm.adapter.FragmentAdapter
import com.github.anglepengcoding.kt_mvvm.base.BaseActivity
import com.github.anglepengcoding.kt_mvvm.base.vmObserverLoading
import com.github.anglepengcoding.kt_mvvm.weight.UpdatePopup
import com.github.mykotlin.R
import com.github.mykotlin.Test
import com.github.mykotlin.model.fragment.classify.ClassifyFragment
import com.github.mykotlin.model.fragment.index.IndexFragment
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.enums.PopupAnimation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val mFragmentList = mutableListOf(IndexFragment.instance, ClassifyFragment.instance)


    private val mainModel by viewModels<MainModel>()


    override fun displayStatusBar(): Boolean {
        return true
    }

    override fun createLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        mTitleText?.text = "测试"
        initViewPager()
    }

    private fun initViewPager() {
        val mTitles: ArrayList<String> = ArrayList()
        mTitles.add("indexFragment")
        mTitles.add("classifyFragment")

        mViewPager.adapter = FragmentAdapter(supportFragmentManager, mFragmentList)
        tab.setViewPager(mViewPager, mTitles)
        tab.onPageSelected(0)
    }

    override fun initData() {
        mainModel.refreshData.vmObserverLoading(this) {
            initPopup(it)
        }
    }


    override fun initPresenter() {
        rxLifeScope.launch {
            mainModel.onRefresh()
        }
    }

    private fun initPopup(it: Test) {
        val popup = XPopup.Builder(mContext)
            .popupAnimation(PopupAnimation.ScaleAlphaFromCenter)
            .dismissOnTouchOutside(true)
            .hasBlurBg(true)
            .dismissOnBackPressed(false)
            .autoDismiss(false)
            .asCustom(
                UpdatePopup(
                    mContext,
                    it.result.versionName,
                    it.result.remark
                )
            ) as UpdatePopup
        popup.show()
        popup.setInterface(object : UpdatePopup.IUpdateCallback {
            override fun recover() {
                finish()
            }

            override fun upgrade() {
                DownloadManager.Builder(this@MainActivity)
                    .apkName("cs.apk")
                    .apkUrl(it.result.url)
                    .smallIcon(R.mipmap.icon_app)
                    .showNotification(true)
                    .build()
                    .download();
            }
        })

    }

}

