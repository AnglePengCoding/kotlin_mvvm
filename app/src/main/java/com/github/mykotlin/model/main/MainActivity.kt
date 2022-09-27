package com.github.mykotlin.model.main

import androidx.activity.viewModels
import androidx.lifecycle.rxLifeScope
import com.azhon.appupdate.manager.DownloadManager
import com.github.anglepengcoding.kt_mvvm.base.BaseActivity
import com.github.anglepengcoding.kt_mvvm.base.vmObserverLoading
import com.github.mykotlin.R
import com.github.anglepengcoding.kt_mvvm.weight.UpdatePopup
import com.github.mykotlin.Test
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.enums.PopupAnimation

class MainActivity : BaseActivity() {

    val mainModel by viewModels<MainModel>()

    override fun displayStatusBar(): Boolean {
        return true
    }

    override fun createLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        mTitleText?.text = "测试"
    }

    override fun initData() {
        mainModel.refreshData.vmObserverLoading(this) {
            initP(it)
        }
    }


    override fun initPresenter() {
        rxLifeScope.launch {
            mainModel.onRefresh()
        }
    }

    private fun initP(it: Test) {
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
                    .apkName("青岛农检.apk")
                    .apkUrl(it.result.url)
                    .smallIcon(R.mipmap.icon_app)
                    .build()
                    .download();
            }
        })

    }

}

