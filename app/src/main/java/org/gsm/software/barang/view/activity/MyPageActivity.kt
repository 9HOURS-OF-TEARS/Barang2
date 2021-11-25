package org.gsm.software.barang.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.gsm.software.barang.R
import org.gsm.software.barang.databinding.ActivityMyPageBinding

class MyPageActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMyPageBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()

    }

    private fun initView(){
        binding.mypageactivity = this
    }

    fun goBack(){
        finish()
    }

}