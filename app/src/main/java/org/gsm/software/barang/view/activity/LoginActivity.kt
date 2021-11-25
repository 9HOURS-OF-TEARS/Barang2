package org.gsm.software.barang.view.activity

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import org.gsm.software.barang.R
import org.gsm.software.barang.databinding.ActivityLoginBinding
import org.gsm.software.barang.view.fragment.RecentlyPostFragment
import org.gsm.software.barang.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val bind by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
        bind.activity = this
    }

    fun goSignup() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun login() {
        loginViewModel.login(bind.email.text.toString(), bind.password.text.toString())
        loginViewModel.registerResponseInt.observe(this, Observer { it ->
            if (it == 1) {
                Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
            } else if (it == 2) {
                loginViewModel.auth.observe(this, Observer {
                    val intent = Intent(this, MainActivity::class.java)
                    var fragment = RecentlyPostFragment
                    var bundle = Bundle()
                    bundle.putString("auth", it)
                    startActivity(intent)
                })
            } else {
                Log.d(ContentValues.TAG, "login: $it")
            }
        })
    }
}