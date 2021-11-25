package org.gsm.software.barang.view.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.MenuItem
import android.view.Window
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator
import org.gsm.software.barang.R
import org.gsm.software.barang.adapter.RankAdapter
import org.gsm.software.barang.databinding.ActivityRankcommentBinding
import org.gsm.software.barang.databinding.MainHeaderBinding

class RankcommentActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val binding by lazy { ActivityRankcommentBinding.inflate(layoutInflater) }
    private lateinit var headB: MainHeaderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        slideInit()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        actionBar()
    }

    fun initView() {
        binding.rankactivity = this
        binding.navigationView.setNavigationItemSelectedListener(this@RankcommentActivity)
        val headV = binding.navigationView.getHeaderView(0)
        headB = MainHeaderBinding.bind(headV)
        val viewpagerFragmentAdapter = RankAdapter(this)
        binding.viewPager.adapter = RankAdapter(this)
        val tabTitles = listOf<String>("예쁜 글 작성자", "예쁜 댓글 작성자")
        TabLayoutMediator(binding.taps, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

    }


    private fun slideInit() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            with(window) {
                requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
                // set an slide transition
                enterTransition = Slide(Gravity.END)
                exitTransition = Slide(Gravity.END)
            }
        }
    }


    // menubar 생성
    fun actionBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        //메뉴바 왼쪽 설정
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.menubar)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }

    //메뉴 버튼
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                binding.drawerLayout.openDrawer(GravityCompat.END)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}