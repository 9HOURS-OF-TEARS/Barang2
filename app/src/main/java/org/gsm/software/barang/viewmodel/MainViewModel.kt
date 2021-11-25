package org.gsm.software.barang.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.gsm.software.barang.model.AllPostResponse
import org.gsm.software.barang.model.MyPostResponse
import org.gsm.software.barang.model.Post
import org.gsm.software.barang.model.users.PostApi
import org.gsm.software.barang.model.users.UserApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val api2: UserApi, val api : PostApi) : ViewModel() {

    val getAllPostNull: LiveData<Boolean> get() = _getAllPostNull
    private val _getAllPostNull: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    val getMyPostNull: LiveData<Boolean> get() = _getMyPostNull
    private val _getMyPostNull: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    val getMyPostResponse: LiveData<MyPostResponse> get() = _getMyPostResponse
    private val _getMyPostResponse = MutableLiveData<MyPostResponse>()

    val getPostResponse: LiveData<List<Post>> get() = _getPostResponse
    private val _getPostResponse = MutableLiveData<List<Post>>()



    //게시물이 없는지 체크 true = 게시물 없음, false = 게시물 있음
    fun setGetAllPostNull(check: Boolean) {
        _getAllPostNull.value = check
    }

    fun getMyPost(auth: String) {
        api2.getMyPost(auth).enqueue(object : Callback<MyPostResponse> {
            override fun onResponse(
                call: Call<MyPostResponse>,
                response: Response<MyPostResponse>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()!!

                } else if (response.body() == null) {
                    _getMyPostNull.value = true
                }
            }

            override fun onFailure(call: Call<MyPostResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    //게시물이 없는지 체크 true = 게시물 없음, false = 게시물 있음
    fun setGetMyPostNull(check: Boolean) {
        _getMyPostNull.value = check
    }

    fun callGetPost(auth: String, criterion: String, duration: String) {
        api.getPost(auth,criterion, duration).enqueue(object : Callback<AllPostResponse>{
            override fun onResponse(
                call: Call<AllPostResponse>,
                response: Response<AllPostResponse>
            ) {
                if(response.isSuccessful){
                    _getPostResponse.value = response.body()?.post
                }
            }

            override fun onFailure(call: Call<AllPostResponse>, t: Throwable) {

            }
        })
    }

    fun setGetPostResponse(getPostResponse :List<Post>? ){
        _getPostResponse.value = getPostResponse!!
    }

}