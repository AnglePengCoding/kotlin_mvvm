package com.github.mykotlin.model.fragment.classify

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.github.anglepengcoding.kt_mvvm.base.BaseFragment
import com.github.anglepengcoding.kt_mvvm.base.vmObserverLoading
import com.github.mykotlin.R
import com.github.mykotlin.Test
import com.github.mykotlin.adapter.ClassifyAdapter
import com.github.mykotlin.model.activity.main.MainRepository
import kotlinx.android.synthetic.main.fragment_classify.*

/**
 * @author AnglePenCoding
 * Created by on 2022/9/27 0027
 * @website https://github.com/AnglePengCoding
 */
class ClassifyFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private val classifyModel by viewModels<ClassifyModel>()
    private val adapter by lazy { ClassifyAdapter() }

    companion object {
        val instance: ClassifyFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ClassifyFragment()
        }
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_classify
    }

    override fun initView() {
        mRefresh.setOnRefreshListener(this::onRefresh)
        mRefresh.isRefreshing = true

        initAdapter()
    }

    private fun initAdapter() {
        mRecyclerView.layoutManager = LinearLayoutManager(mContext)
        mRecyclerView.adapter = adapter
    }

    override fun initData() {
        classifyModel.refresh.vmObserverLoading(this) {
            mRefresh.isRefreshing = false
            adapter.setNewInstance(it.result.list)
        }

        classifyModel.more.vmObserverLoading(this) {

        }
    }

    override fun initPresenter() {
        onRefresh()
    }

    override fun onRefresh() {
        classifyModel.onRefresh()
    }

}