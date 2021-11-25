package org.gsm.software.barang.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.gsm.software.barang.model.RequestRegister
import org.gsm.software.barang.model.users.UserApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SinupViewModel(val api: UserApi) : ViewModel() {

    private val _check = MutableLiveData<Boolean>()
    val check: LiveData<Boolean> get() = _check

    private val _registerResultInt = MutableLiveData<String>()
    val registerResponseInt: LiveData<String> get() = _registerResultInt

    private val _registerResult = MutableLiveData<String>()
    val registerResponse: LiveData<String> get() = _registerResult

    private val _checkResult = MutableLiveData<String>()
    val checkResult: LiveData<String> get() = _checkResult

    private val _checkPoint = MutableLiveData<Int>()
    val checkPoint: LiveData<Int> get() = _checkPoint

    init {
        _checkPoint.value = 0
        _check.value = false
    }

    fun sameNameCheck(data: String) {
        api.nameCheck(data).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    _checkResult.value = "사용가능한 닉네임 입니다."
                    _checkPoint.value = _checkPoint.value?.plus(1)
                    _check.value = true
                } else {
                    _checkResult.value = "이미있는 닉네임 입니다."
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                _checkResult.value = "$t"
            }
        })
    }

    fun sameIdCheck(data: String) {
        api.idCheck(data).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    _checkResult.value = "사용가능한 아이디 입니다."
                    _checkPoint.value = _checkPoint.value?.plus(1)
                    _check.value = true
                } else {
                    _checkResult.value = "이미있는 아이디 입니다."
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                _checkResult.value = t.toString()
            }
        })
    }

    fun register(email: String, pwd: String, nick: String) {
        if (email.isEmpty() || pwd.isEmpty() || nick.isEmpty()) {
            _registerResult.value = "모두 입력해주세요"
            _registerResultInt.value = "4"
        } else {
            api.register(RequestRegister(id = email, password = pwd, nickname = nick))
                .enqueue(object : Callback<Void> {
                    override fun onResponse(
                        call: Call<Void>,
                        response: Response<Void>
                    ) {
                        if (response.isSuccessful) {
                            _registerResult.value = "회원가입 성공"
                            _registerResultInt.value = "1"
                        } else {
                            _registerResult.value = response.code().toString()
                            _registerResultInt.value = "2"
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        _registerResult.value = "$t"
                        _registerResultInt.value = "3"
                    }
                })
        }
    }
}