package com.github.mykotlin

import rxhttp.wrapper.annotation.DefaultDomain

/**
 * @author AnglePenCoding
 * Created by on 2022/9/23 0023
 * @website https://github.com/AnglePengCoding
 */
class Constants {

    companion object{

        @DefaultDomain //设置为默认域名
        const val WAN_ANDROID_BASEURL = "http://hgz.app.creditmoa.com/"

        const val WAN_ARTICLE = "version/getLatestOne"

    }
}