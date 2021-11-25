package org.gsm.software.barang.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.gsm.software.barang.view.fragment.PrettyCommentFragment
import org.gsm.software.barang.view.fragment.PrettyContentFragment

class RankAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    val fragmentList = listOf<Fragment>(PrettyContentFragment(), PrettyCommentFragment())

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}