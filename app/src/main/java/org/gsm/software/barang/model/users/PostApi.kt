package org.gsm.software.barang.model.users

import org.gsm.software.barang.model.AllPostResponse
import org.gsm.software.barang.model.LoginRequest
import org.gsm.software.barang.model.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface PostApi {
    @GET("post")
    fun getPost(
        @Header("Authorization") authorization :String,
        @Query("criterion") criterion : String,
        @Query("duration") duration : String
    ):Call<AllPostResponse>

    @POST("user/login")
    fun login(
        @Body userData : LoginRequest
    ):Call<LoginResponse>

}