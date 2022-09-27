package com.github.anglepengcoding.kt_mvvm.adapter

import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Created by Yuang on 17/12/8.
 * Summary:FragmentAdapter
 */
class FragmentAdapter(fm: FragmentManager?, private val fragments: List<Fragment>) :
    FragmentStatePagerAdapter(
        fm!!
    ) {
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        super.destroyItem(container, position, object);
    }
}