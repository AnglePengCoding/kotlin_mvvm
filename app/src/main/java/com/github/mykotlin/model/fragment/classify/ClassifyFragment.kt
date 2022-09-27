package com.github.mykotlin.model.fragment.classify

import androidx.fragment.app.viewModels
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.github.anglepengcoding.kt_mvvm.base.BaseFragment
import com.github.anglepengcoding.kt_mvvm.base.vmObserverLoading
import com.github.mykotlin.R
import com.github.mykotlin.Test
import kotlinx.android.synthetic.main.fragment_classify.*

/**
 * @author AnglePenCoding
 * Created by on 2022/9/27 0027
 * @website https://github.com/AnglePengCoding
 */
class ClassifyFragment : BaseFragment() {

    private val classifyModel by viewModels<ClassifyModel>()
    private lateinit var adapter: BaseQuickAdapter<Test, BaseViewHolder>

    companion object {
        val instance: ClassifyFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ClassifyFragment()
        }
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_classify
    }

    override fun initView() {
        initAdapter()
    }

    private fun initAdapter() {
    }

    override fun initData() {
        classifyModel.refresh.vmObserverLoading(this) {
            mTv.text = it.result.list?.get(0)?.eName
        }

        classifyModel.more.vmObserverLoading(this) {

        }
    }

    override fun initPresenter() {
        classifyModel.onRefresh()
    }
}