package com.github.mykotlin.model.fragment.index

import android.annotation.SuppressLint
import com.github.anglepengcoding.kt_mvvm.base.BaseFragment
import com.github.mykotlin.R
import kotlin.properties.Delegates

/**
 * @author AnglePenCoding
 * Created by on 2022/9/27 0027
 * @website https://github.com/AnglePengCoding
 */
class IndexFragment : BaseFragment() {

    companion object {
        val instance: IndexFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            IndexFragment()
        }
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_index
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun initPresenter() {
    }

}