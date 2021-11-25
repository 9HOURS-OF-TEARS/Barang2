package org.gsm.software.barang.view.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator
import org.gsm.software.barang.R
import org.gsm.software.barang.adapter.ViewPagerFragmentAdapter
import org.gsm.software.barang.databinding.ActivityMainBinding
import org.gsm.software.barang.databinding.MainHeaderBinding
import org.gsm.software.barang.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var headB: MainHeaderBinding
    private val mainViewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        slideInit()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        actionBar()
    }

    fun initView(){
        binding.activity = this
        binding.navigationView.setNavigationItemSelectedListener(this@MainActivity)
        val headV = binding.navigationView.getHeaderView(0)
        headB = MainHeaderBinding.bind(headV)
        headB.headAactivity = this@MainActivity
        val viewpagerFragmentAdapter = ViewPagerFragmentAdapter(this)
        binding.viewPager.adapter = ViewPagerFragmentAdapter(this)
        val tabTitles = listOf<String>("최신글","인기글")
        TabLayoutMediator(binding.taps,binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

    }

    private fun slideInit(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            with(window) {
                requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
                // set an slide transition
                enterTransition = Slide(Gravity.END)
                exitTransition = Slide(Gravity.END)
            }
        }
    }
    // menubar 생성
    private fun actionBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.my_page-> {
                startActivity(Intent(this,MyPageActivity::class.java))
            }
            R.id.rangking->{
                startActivity(Intent(this,RankcommentActivity::class.java))
            }
            else -> {}
        }
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    //메뉴 버튼
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.menuAction -> {
                binding.drawerLayout.openDrawer(GravityCompat.END)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}