package org.gsm.software.barang.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.gsm.software.barang.model.LoginRequest
import org.gsm.software.barang.model.LoginResponse
import org.gsm.software.barang.model.users.PostApi
import org.gsm.software.barang.model.users.UserApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val api : UserApi, private val api2 : PostApi):ViewModel(){
    private val _toastM = MutableLiveData<String>()
    val toastM : LiveData<String> get() =  _toastM

    private val _registerResultInt = MutableLiveData<Int>()
    val registerResponseInt: LiveData<Int> get() = _registerResultInt

    private val _auth = MutableLiveData<String>()
    val auth : LiveData<String> get () = _auth

    fun login(id:String,password:String){
        if(id.isEmpty() || password.isEmpty() ){
            _toastM.value = "아이디와 비밀번호를 모두 입력해주세요"
            _registerResultInt.value = 1
        }else{
            api2.login(LoginRequest(id = id,password = password)).enqueue(object : Callback<LoginResponse>{
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                   if (response.isSuccessful){
                       _toastM.value = "로그인 성공"
                       _registerResultInt.value = 2
                       _auth.value = response.body()!!.access_token
                   }else if(response!!.code() in 400..499){
                        _toastM.value  = "요청오류"
                   }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    _toastM.value = "$t"
                }
            })
        }
    }
}