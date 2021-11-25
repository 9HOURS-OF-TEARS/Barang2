package org.gsm.software.barang.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.gsm.software.barang.view.fragment.PopularPostFragment
import org.gsm.software.barang.view.fragment.RecentlyPostFragment

class ViewPagerFragmentAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    private val fragmentList = listOf<Fragment>(RecentlyPostFragment(),PopularPostFragment())

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}