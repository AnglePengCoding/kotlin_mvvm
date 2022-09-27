package com.github.mykotlin.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.github.mykotlin.R
import com.github.mykotlin.bean.ListItem

/**
 * @author AnglePenCoding
 * Created by on 2022/9/27 0027
 * @website https://github.com/AnglePengCoding
 */
class ClassifyAdapter() :
    BaseQuickAdapter<ListItem, BaseViewHolder>(R.layout.item_classify) {

    override fun convert(holder: BaseViewHolder, item: ListItem) {
        holder.setText(R.id.mTv,item.eName)
    }
}