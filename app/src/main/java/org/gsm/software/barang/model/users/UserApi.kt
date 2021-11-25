package org.gsm.software.barang.model.users

import okhttp3.ResponseBody
import org.gsm.software.barang.model.MyPostResponse
import org.gsm.software.barang.model.RequestRegister
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface UserApi {


    @POST("sign-up")
    fun register(
        @Body userData : RequestRegister
    ):Call<Void>

    @HEAD("check/id")
    fun idCheck(
        @Query("key") key : String
    ):Call<Void>

    @HEAD("check/nickname")
    fun nameCheck(
        @Query("key") key : String
    ):Call<Void>

    @GET("user/posts")
    fun getMyPost(
        @Header("Authorization") authorization : String
    ):Call<MyPostResponse>
}