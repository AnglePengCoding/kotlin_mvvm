package com.github.anglepengcoding.kt_mvvm.base

import android.app.Application
import me.jessyan.autosize.AutoSize
import me.jessyan.autosize.AutoSizeConfig
import okhttp3.OkHttpClient
import rxhttp.RxHttpPlugins.init
import kotlin.properties.Delegates

/**
 * @author AnglePenCoding
 * Created by on 2022/9/22 0022
 * @website https://github.com/AnglePengCoding
 */
open class BaseApp : Application() {
    companion object {
        var instance: BaseApp by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        initRxhttp()
        AutoSize.initCompatMultiProcess(this)
        AutoSizeConfig.getInstance()
            .setBaseOnWidth(true)
            .isCustomFragment = true
    }

    open fun initRxhttp() {
        val builder = OkHttpClient.Builder()
//        builder.addInterceptor(ChuckInterceptor(applicationContext))
        //或者，调试模式下会有日志输出
        init(builder.build()).setDebug(true)
    }
}