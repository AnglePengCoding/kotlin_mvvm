package com.github.anglepengcoding.kt_mvvm.weight

import android.app.Activity
import android.content.Context
import com.azhon.appupdate.manager.DownloadManager
import com.github.anglepengcoding.kt_mvvm.R
import com.jakewharton.rxbinding4.view.clicks
import com.lxj.xpopup.core.CenterPopupView
import kotlinx.android.synthetic.main.popup_update.view.*
import java.util.concurrent.TimeUnit

/**
 * @author AnglePenCoding
 * Created by on 2022/9/26 0026
 * @website https://github.com/AnglePengCoding
 */
open class UpdatePopup(
    context: Context, var versionNum: String,
    var content: String
) : CenterPopupView(context) {


     lateinit var callback: IUpdateCallback


    override fun onCreate() {
        super.onCreate()

        mLottie2!!.setAnimation("update.json")
        mLottie2.playAnimation()

        versionName.text = versionNum
        remark.text = content


        cancel.clicks()
            .throttleFirst(2000, TimeUnit.MICROSECONDS)
            .subscribe {
                if (popupInfo.isDismissOnTouchOutside == false && popupInfo.isDismissOnBackPressed == false && popupInfo.autoDismiss == false) {
                    callback.recover()
                } else {
                    dismiss()
                }
            }


        update.clicks().throttleFirst(2000, TimeUnit.MICROSECONDS)
            .subscribe {
                callback.upgrade()
                dismiss()
            }
    }

    override fun getImplLayoutId(): Int {
        return R.layout.popup_update
    }

    override fun onDestroy() {
        mLottie2!!.cancelAnimation()
        super.onDestroy()
    }

    fun setInterface(callback: IUpdateCallback) {
        this.callback = callback
    }


    interface IUpdateCallback {
        fun recover()
        fun upgrade()
    }

}