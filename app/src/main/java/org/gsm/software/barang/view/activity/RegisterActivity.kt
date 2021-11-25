package org.gsm.software.barang.view.activity

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelStoreOwner
import com.google.android.material.textfield.TextInputLayout
import org.gsm.software.barang.R
import org.gsm.software.barang.databinding.ActivityRegisterBinding
import org.gsm.software.barang.viewmodel.SinupViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity(), ViewModelStoreOwner {
    private val bind by lazy { ActivityRegisterBinding.inflate(layoutInflater) }
    private val viewmodel: SinupViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
        bind.activity = this
    }

    fun goLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    fun idCheck() {
        viewmodel.sameIdCheck(bind.signupEmail.text.toString())
        viewmodel.check.observe(this, Observer {
            if (it) {
                viewmodel.checkResult.observe(this, Observer {
                    Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
                })
            } else {
                viewmodel.checkResult.observe(this, Observer {
                    Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
                })
            }
        })
    }

    fun nameCheck() {
        viewmodel.sameIdCheck(bind.signupNickname.text.toString())
        viewmodel.check.observe(this, Observer {
            if (it) {
                viewmodel.checkResult.observe(this, Observer {
                    Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
                })
            } else {
                viewmodel.checkResult.observe(this, Observer {
                    Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
                })
            }
        })
    }

    fun signup() {
        viewmodel.register(
            bind.signupEmail.text.toString(),
            bind.signupPw.text.toString(),
            bind.signupNickname.text.toString()
        )

        viewmodel.registerResponseInt.observe(this, Observer { it ->
            if (it == "1") {
                Toast.makeText(this, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else if (it == "2") {
                viewmodel.registerResponse.observe(this, Observer {
                    Log.d(ContentValues.TAG, "signup: ${it}")
                })
            } else if (it == "3") {
                viewmodel.registerResponse.observe(this, Observer {
                    Log.d(ContentValues.TAG, "signup: $it")
                })
            } else {
                viewmodel.registerResponse.observe(this, Observer {
                    Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
                })
            }

        })


    }

    fun checkEmaill(): Boolean {
        val email = bind.signupEmail.text.toString().trim()
        val emailValidation =
            "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\$"
        val p = Pattern.matches(emailValidation, email) // 서로 패턴이 맞닝?
        return p && email.length > 6
    }

    fun checkNickName(): Boolean {
        val nickname = bind.signupNickname.toString().trim()
        val nameValidation = "^[a-zA-Z가-힣]*\$ "
        val p = Pattern.matches(nameValidation, nickname)
        return p
    }

    private fun textWatcher() {
        bind.signupEmail.addTextChangedListener(object : TextWatcher,
            TextInputLayout.OnEditTextAttachedListener {
            override fun afterTextChanged(s: Editable?) {
                // text가 변경된 후 호출
                // s에는 변경 후의 문자열이 담겨 있다.
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // text가 변경되기 전 호출
                // s에는 변경 전 문자열이 담겨 있다.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // text가 바뀔 때마다 호출된다.
                // 우린 이 함수를 사용한다.

            }

            override fun onEditTextAttached(textInputLayout: TextInputLayout) {
                checkNickName()
                checkEmaill()
            }
        })
    }

}


